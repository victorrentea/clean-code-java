package sample.two;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.function.Predicate;

// IM = Interaction Management
public class Two {
  private static final Logger logger = LoggerFactory.getLogger(Two.class);
  private void requestStopRecording(Decision decision, DataSegment dataSegment, IMRecording imRecording, IMRecordableParticipant recordableParticipant) {
    try {
      StopRecordRequest stopRecordRequest = recorderRequestCreator.createStopRecordRequest(dataSegment, imRecording, recordableParticipant, featureToggleManager);
      recorderProvider.getRecorder(decision.getMediaType())
          .ifPresent(recorder -> {
            try { // called 10 millions of time per day!!
              logger.info("Request to stop recording received for recording: {0}", imRecording);
              final RecordResponse recordResponse = getStopRecordResponse(decision, imRecording, stopRecordRequest, recorder);
              // TODO victorrentea 2024-01-31: funct in var
              //we are recording screen and voice
              final Predicate<Decision> isMaskReason = d -> d.getReason().equals(IMRecordingTriggerType.mask.name());
              final Predicate<Decision> isScreenMediaType = d -> d.getMediaType().equals(MediaType.SCREEN); // TODO victorrentea 2024-01-31: enum ==
              final Predicate<RecordResponse> isMaskingFailure = r -> {
                if (Objects.nonNull(r.getRecordingStatus().getMasking())) {
                  return r.getRecordingStatus().getMasking()
                             .stream()
                             .filter(maskingData -> "masking failure".equalsIgnoreCase(maskingData.getStatus())) // TODO victorrentea 2024-01-31:  magic string
                             .count() > 0;
                }
                return false;
              };

              if (isMaskReason.and(isScreenMediaType).test(decision) && isMaskingFailure.test(recordResponse)) {
                final IMRecording maskedRecording = new IMRecording(
                    imIdGenerator.generateId(),
                    MediaType.SCREEN,
                    recordResponse.getRecordingStatus().getEndTime(),
                    IMRecordingTriggerType.maskingFailure.name(),
                    imRecording.getRecordableParticipant());
                maskedRecording.setMasking(recordResponse.getRecordingStatus().getMasking());
                maskedRecording.setRecordingStatusCode(RecordingStatusCode.ERROR);
                maskedRecording.setMaskingFailureStartTime(recordResponse.getRecordingStatus().getEndTime());
                maskedRecording.setRecorderId(imRecording.getRecorderId());
                maskedRecording.setTaskId(imRecording.getTaskId());
                dataSegment.addRecording(maskedRecording);
              }

              if (imRecording.getMediaType() == MediaType.VOICE) {
                removeContinuityCommandFromCache(dataSegment, imRecording.getRecordingId());
              }
              if (Objects.nonNull(recordResponse.getRecordingStatus())) {
                imRecording.setMasking(recordResponse.getRecordingStatus().getMasking());
              }
              OffsetDateTime reportedFromRecorderEndTime = getReportedFromRecorderEndTime(recordResponse.getRecordingStatus().getEndTime());
              detectPartialRecordingAndChangeStatus(recordResponse);
              imRecording.endRecording(reportedFromRecorderEndTime,
                  decision.getReason(),
                  recordResponse.getRecordingStatus().getStatus(), // TODO victorrentea 2024-01-31: pass it all ? NO, too big, from another microservice
                  recordResponse.getRecordingStatus().getRecordingError());

              if (decision.getReason().equals(IMRecordingTriggerType.sod.name()) &&
                  recordResponse.getRecordingStatus().getRecordingError() == null) {
                addSodMarkerToSegmentErrorType(imRecording); // SOD = Stop on demand
                logger.info("SOD recording was added as a recording error for recording: {0}", imRecording.toString());
              }

              addRecodingErrorInCaseOfCustomerRestrictedRecording(imRecording, stopRecordRequest, recordResponse);

              OffsetDateTime startTime = recordResponse.getRecordingStatus().getStartTime();
              if (startTime != null) {
                IMRecording imRecordingToUpdate = updateStartTime(dataSegment, imRecording, startTime);
                if (imRecordingToUpdate.getStartTime().isAfter(imRecordingToUpdate.getEndTime())) {
                  logger.info("Start recording time higher than End recording time - Update recording time. RecordingID: ",
                      imRecording.getRecordingId());
                  imRecordingToUpdate.setEndTime(imRecordingToUpdate.getStartTime());
                  if (dataSegment.getAllRecording()
                          .stream()
                          .filter(x -> x.getRecordingId().equals(imRecording.getRecordingId()))
                          .count() == 2) { // TODO victorrentea 2024-01-31: magic
                    updateStartTime(dataSegment, imRecording, imRecordingToUpdate.getEndTime());
                  }
                }
                setRecordingStatus(imRecording, imRecordingToUpdate);
              } else {
                List<IMRecording> recordings = dataSegment.getAllRecording()
                    .stream()
                    .filter(x -> x.getRecordingId().equals(imRecording.getRecordingId()))
                    .collect(Collectors.toList());
                if (recordings.size() == 2) {
                  recordings.stream()
                      .filter(x -> x.getCloseReason().equals(IMRecordingTriggerType.WrapUp.name()))
                      .findFirst()
                      .ifPresent(recToUpdate -> setRecordingStatus(imRecording, recToUpdate));
                }
              }
              if (imRecording.getStartReason().equals(IMRecordingTriggerType.WrapUp.name())) {
                handleCallDisposition(dataSegment, imRecording);
              }
              if (!imRecording.getCloseReason().equals(IMRecordingTriggerType.WrapUp.name())) {
                imRecording.setDuringMasking(false);
              }
            } catch (Exception e) {
              updateRecordingWithMaskingFailure(decision, imRecording, recorder);
              logger.error("Failed to stop recording media: {0}", e, imRecording);
            } finally {
              if (logger.isDebugEnabled()) {
                logger.debug("Request to stop recording received for recording handled: {0}", imRecording);
              }
              if (imRecording.getStartReason().equals(IMRecordingTriggerType.WrapUp.name())) {
                clearTimerJobEntries(dataSegment);
              }
            }
          });
    } catch (Exception ex) { // TODO victorrentea 2024-01-31: too broad. what exceptions?
      logger.error("Failed to stop recording: {0}", ex, imRecording);
    }
  }
}
