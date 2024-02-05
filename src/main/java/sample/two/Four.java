//package sample.two;
//
//public class Four {
//  private String getUserIdFromRequest(DataSegment dataSegment) {
//    Optional<IMRecording> firstWrapUpRecording = dataSegment.getFirstWrapUpRecording();
//    if (firstWrapUpRecording.isPresent()) {
//        IMRecordableParticipant recordableParticipant = firstWrapUpRecording.get().getRecordableParticipant();
//        if (!(recordableParticipant instanceof IMAgentParticipant)) {
//            logger.error("found participant which is not agent on wrap up recording");
//            return null;
//        }
//        return String.valueOf(recordableParticipant.getUserId());
//    }
//    return null; /// uuuuu not OPtional.
//}
//
//}
