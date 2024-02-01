package sample.two;

import java.util.Set;

import static com.fasterxml.jackson.databind.type.LogicalType.Collection;

public class Three {
  //  @Override
  public void handle(ImEvent imEvent, MasterDetails masterDetails) {
    InContactEvent callEvent = (InContactEvent) imEvent;

    final String callEventSessionId = callEvent.getSessionId();
    // TODO victorrentea 2024-01-31: is... means boolean -> extract bvariable
    // CTI is the source of events: start call ,end call, transfer.
    // Segment = a part of the call with different participants. We might create a new segment after a transfer to another agent.
    // the interaction user - center = multiple segments.
    // CTI segment = beginning.
    // Data segmnent = end.

    // The master details contains the CTI  segments.
    // Later from CTI segment -> data segment.

    CTISegment currentCTISegment = masterDetails.getSegment(CTISegment.isSessionOpenAndIdEquals(callEventSessionId));

    if (StringUtils.isNotBlank(callEvent.getBusinessEntities())) {
      updateBusinessDataAndSendSegmentIfNeeded(masterDetails, callEvent, callEventSessionId, currentCTISegment);
    }

    if (currentCTISegment == null) {
      logger.warn("Disconnect event with unknown SessionId: {0} - ignoring.", callEventSessionId);
      return;
    }

    if (handleEmailEventsThatAccidentallyArrivedToDisconnectHandler(imEvent, masterDetails, callEvent)) {
      return;
    }

    Set<String> disconnectedSessions = updateDisconnectedSessionList(callEvent);

    logDisconnectedSessions(disconnectedSessions);

    // TODO victorrentea 2024-01-31: Encapsulate Collection?
    List<SessionEntity> segmentSessionEntities = currentCTISegment.getSessionEntities();

    if (segmentSessionEntities.size() == 2 && // TODO victorrentea 2024-01-31: explanatory varialbes
        segmentSessionEntities.stream().map(SessionEntity::getImParticipants).mapToLong(Collection::size).sum() == 2 &&
        segmentSessionEntities.stream().noneMatch(s -> s.getSessionEntitySTATE() == CallState.CLOSED)) {

      for (SessionEntity sessionEntity : segmentSessionEntities) {
        if (sessionEntity.getSessionId().equals(callEventSessionId)) {
          sessionEntity.setSessionEntitySTATE(CallState.CLOSED);
          List<IMParticipant> imParticipants = sessionEntity.getImParticipants();
          for (IMParticipant imParticipant : imParticipants) {
            if (imParticipant instanceof IMAgentParticipant && callEvent.isDispositionExpected()) {
              ((IMAgentParticipant) imParticipant).setWrapupStateCti(WRAPUP_STATE_CTI.EXPECTED);
            }
          }
        }
      }

      if (segmentSessionEntities.stream()
          .filter(s -> !s.getSessionId().equals(callEventSessionId))
          .map(SessionEntity::getImParticipants)
          .flatMap(Collection::stream).anyMatch(IMOrganizationParticipant.class::isInstance)) {
        return;
      }
    }

    markAllDisconnectedSessionsAsClosed(disconnectedSessions, segmentSessionEntities);

    if (currentCTISegment.getEndTime() == null) {
      closeIMSegment(currentCTISegment);
      //Disposition before disconnect
      if (callEvent.isDispositionExpected() && !agentExistInOtherNotClosedSession(callEvent, currentCTISegment)) {
        currentCTISegment.setAgentWrapUpStateByAgentUUID(callEvent.getAgentUUId(), WRAPUP_STATE_CTI.EXPECTED);
      }
      currentCTISegment.setRecordingStatementId(callEvent.getRecordingStatementId());
      imSegmentManager.sendSegment(currentCTISegment, DataSegmentActionType.STOP);
    }

    if (currentCTISegment.getOpenParticipants().size() < 2) {
      return;
    }
    // CTI = Computer Telephony Integration : "phone driver" events

    //take out open sessions from segment <<< WOW a remorse-comment // TODO victorrentea 2024-01-31:
    List<SessionEntity> openSessionEntitiesClones = currentCTISegment // TODO victorrentea 2024-01-31: huge?
        .getSessionEntitiesByCondition(x -> x.getSessionEntitySTATE() != CallState.CLOSED)
        .stream()
        .map(SessionEntity::duplicate)
        .collect(Collectors.toList()); // toList

    currentCTISegment.getSessionEntities()
        .forEach(s -> s.setSessionEntitySTATE(CallState.CLOSED)); // TODO victorrentea 2024-01-31:  dedicated method ? we control SessionEntity

    if (openSessionEntitiesClones.isEmpty()) {
      return;
    }

    logger.info("Disconnect session(s) after closing segment: {0}", currentCTISegment.getId());

    CTISegment afterDisconnectSegment = segmentCreationLogic.createSegmentFromSessionList(openSessionEntitiesClones, masterDetails,
        TelephonySource.inContactACD, RecordingType.MONO, callEvent);
    String sipEndPoint = callEvent.getSipEndPoint();
    String sessionId = getAgentSessionId(openSessionEntitiesClones);

    SipEndPointUpdater.setMediaServerFields(afterDisconnectSegment, sipEndPoint, sessionId, InContactEventType.Disconnected);

    if (!ObjectUtils.isEmpty(currentCTISegment.getMediaServerFields()) && InContactEventType.CallConferenced.equals(currentCTISegment.getMediaServerFields().getSessionState())) {
      Optional<MediaServerFields> mediaServerFields = retrieveAliveSession(masterDetails);
      mediaServerFields.ifPresentOrElse(x -> SipEndPointUpdater.update(afterDisconnectSegment, x.getSipEndPoint(), x.getXTargetId()),
          () -> SipEndPointUpdater.update(afterDisconnectSegment, sipEndPoint, sessionId));
    } else {
      SipEndPointUpdater.update(afterDisconnectSegment, sipEndPoint, sessionId);
    }

    afterDisconnectSegment.setOpenReasonType(OpenCloseReasonType.SEGMENT);

    unmaskIfMaskingInvokerDisconnects(masterDetails, callEvent, afterDisconnectSegment);

    if (afterDisconnectSegment.getParticipants().size() > 1) {
      imSegmentManager.sendSegment(afterDisconnectSegment, DataSegmentActionType.START);
    }
  }


  private boolean isAgentRecordable(IMAgentParticipant imAgentParticipant, DataSegment imSegment) {
    boolean canBeRecorded = false;
    final UUID agentUserId = imAgentParticipant.getUserId();

    if (agentUserId != null) {
      final String userKey = imSegment.getTenantId() + ":" + agentUserId;
      Optional<UserInfo> optionalUserInfo = getUserInfoFromRemoteCache(userKey);
      boolean shouldGetUserFromUserManager =
          isRequiredGetUserFromUserManager(optionalUserInfo, imAgentParticipant, imSegment.getChannelType());
      final boolean isChannelOfTypeText = isChannelOfTypeText(imSegment.getChannelType());

      if (!shouldGetUserFromUserManager) {
        UserInfo userInfoFromCache = optionalUserInfo.get();
        UserRecordable userRecordable = userRecordableConverter.apply(userInfoFromCache);
        logger.info("User info, user key: {0} received from remote cache, is recordable voice: {1}, is recordable screen: {2}",
            userKey,
            userRecordable.isVoiceRecordable(),
            userRecordable.isScreenRecordable()
        );
        canBeRecorded = recordableUserToRecordableMapper.isUserRecordable(isChannelOfTypeText, userRecordable);

        updateIMAgentParticipantWithMissingAgentInfo(imAgentParticipant,
            imSegment.getChannelType(),
            userInfoFromCache.getFullName(),
            userInfoFromCache.getTeamId(),
            userRecordable);
        recordableUserToRecordableMapper.updateRecordingStatusInSegment(isChannelOfTypeText, userRecordable, imSegment);
      } else {
        try {
          User user = getUserFromUserManager(agentUserId.toString());
          if (user != null) {
            UserRecordable userRecordable = getCanBeRecordedAttribute(user);
            canBeRecorded = recordableUserToRecordableMapper.isUserRecordable(isChannelOfTypeText, userRecordable);

            updateIMAgentParticipantWithMissingAgentInfo(imAgentParticipant,
                imSegment.getChannelType(),
                user.getFullName(),
                user.getTeamId(),
                userRecordable);
            recordableUserToRecordableMapper.updateRecordingStatusInSegment(isChannelOfTypeText, userRecordable, imSegment);
            updateCache(imSegment.getTenantId(), user);
          } else {
            logger.warn("Agent not found, userId: {0}", agentUserId.toString());
          }
        } catch (Exception e) {
          logger.error("Failed to retrieve user key: {0} from user manager for the: {1} time", agentUserId.toString(), MAX_TRIES);
        }
      }
    }
    logger.info("Is agent recordable: {0}, agent: {1}", canBeRecorded, imAgentParticipant);
    return canBeRecorded;
  }
}
