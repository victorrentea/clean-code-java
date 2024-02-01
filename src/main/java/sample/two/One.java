package sample.two;

import java.util.List;

// ivr = interactive voice response ("voce robot")
public class One {
  @Override
  public void stopSegment(DataSegment dataSegment) {
    boolean ivrStopSegment = dataSegment.getChannelType() == ChannelType.PHONE_CALL_IVR;
    logger.info(ivrStopSegment ? "Stop IVR segment" : "Stop segment");
    boolean allParticipantsAreNonOrganizationParticipants = isAllParticipantsAreNonOrganizationParticipants(dataSegment);
    List<Decision> recordingDecisions = Lists.newArrayList();
    // TODO: Add Dialer / refactor to handlers
    if (ivrStopSegment) {
      stopIVRSegment(dataSegment, recordingDecisions);
    } else if (allParticipantsAreNonOrganizationParticipants) {
      if (dataSegment.getDialerParticipant().isPresent()) {
        stopRecordDialer(dataSegment);
      } else {
        stopRecordOffPlatform(dataSegment);
      }
    } else {
      recordingDecisions.addAll(recordingDecisionManager.decide(dataSegment, IMRecordingTriggerType.segmentEnd));
    }
    handleParallelScreen(recordingDecisions, this::sendUnMaskScreenRecording);
    processStopDecisions(recordingDecisions);

    if (dataSegment.getTelSource() != TelephonySource.AmazonConnect) {
      pushToProducer(dataSegment);
    }
    processStartDecisions(recordingDecisions);
  }

  public void stopSegment(DataSegment dataSegment) {
    // Your existing code
  }

  private boolean isAllParticipantsAreNonOrganizationParticipants(DataSegment dataSegment) {
    // TODO: implement this method
    return false;
  }

  private void stopIVRSegment(DataSegment dataSegment, List<Decision> recordingDecisions) {
    // TODO: implement this method
  }

  private void stopRecordDialer(DataSegment dataSegment) {
    // TODO: implement this method
  }

  private void stopRecordOffPlatform(DataSegment dataSegment) {
    // TODO: implement this method
  }

  private void sendUnMaskScreenRecording() {
    // TODO: implement this method
  }

  private void processStopDecisions(List<Decision> recordingDecisions) {
    // TODO: implement this method
  }

  private void pushToProducer(DataSegment dataSegment) {
    // TODO: implement this method
  }

  private void processStartDecisions(List<Decision> recordingDecisions) {
    // TODO: implement this method
  }

  private void handleParallelScreen(List<Decision> recordingDecisions, Runnable actionProvider) {
    // TODO: implement this method
  }
}
}
