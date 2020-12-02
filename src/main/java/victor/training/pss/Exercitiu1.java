//package victor.training.pss;
//
//public class Exercitiu1 {
//   /*
//    * find if enable/disable the INsert, Pending buttons, depending by some conditions
//    * Here are also verified if all necessary accounts are correctly loaded (mandatory parameters!)
//    */
//   private void enableButtonsDependingByConditions() {
//      boolean enableInsert = true;
//      boolean enablePending = true;
//      PeriodBo period = getModelPeriod();
//      short status = 0;
//      if (period != null) {
//         status = period.getStatus();
//      }
//
//      String moduleState = getModelModuleState();
//      enablePending = isEnablePending(status, moduleState);
//      enableInsert = isEnableInsert(enableInsert, status, moduleState);
//
//      //enable/disable the 2 buttons using the variables set before
//
//      enableInsertButton(enableInsert);
//      enablePendingButton(enablePending);
//
//      // 2015-05-15 Pending and print buttons are permanently disabled
//      // In order to use the previous behavior, just remove the following lines
//
//      enablePendingButton(false);
//      getButtonById(ModuleStatesConstants.STATE_ADD, ButtonsPanel.BTN_4).setState(PssControlsSettings.DISABLED);
//   }
//
//   private boolean isEnableInsert(boolean enableInsert, short status, String moduleState) {
//      if (moduleState.equals(CashOfficeFCCMaintConstants.STATE_FCC)) {
//         if (status != CashOfficeFCCMaintConstants.PERIOD_STATUS_PENDING && status != CashOfficeFCCMaintConstants.PERIOD_STATUS_OPENED) { //error
//            enableInsert = false;
//         }
//      }
//
//
//      boolean accountsParamLoaded = verifyLoadedAccounts();
//      enableInsert = enableInsert && accountsParamLoaded;
//
//      //when loaded as FCC: disable Insert if exist operators in pending for selected period.
//      if (moduleState.equals(CashOfficeFCCMaintConstants.STATE_FCC)) {
//         boolean operatorsInPending = getModelOperatorsInPending();
//         enableInsert = enableInsert && (!operatorsInPending);
//         //set module variable for know to show a message, later, when the table is filled with data
//         if (operatorsInPending) {
//            showMsgforOperatorsInPending = true;
//         } else {
//            //reset the previous value (necessary when enter here when change to another store)
//            showMsgforOperatorsInPending = false;
//         }
//      }
//      return enableInsert;
//   }
//
////   private boolean isEnablePending(short status, String moduleState) {
////      if (!moduleState.equals(CashOfficeFCCMaintConstants.STATE_FCC)) {
////         return true;
////      }
////      if (status == CashOfficeFCCMaintConstants.PERIOD_STATUS_PENDING) {
////         return false;
////      } else if (status == CashOfficeFCCMaintConstants.PERIOD_STATUS_OPENED) {
////         return true;
////      } else { //error
////         return false; // nu e in spec asta!!!
////      }
////   }
//   private boolean isEnablePending(short status, String moduleState) {
//      return !moduleState.equals(CashOfficeFCCMaintConstants.STATE_FCC) ||
//             status == CashOfficeFCCMaintConstants.PERIOD_STATUS_OPENED;
//   }
//
//   private String getModelModuleState() {
//      return null;
//   }
//
//   private PeriodBo getModelPeriod() {
//      return null;
//   }
//
//   //
////--- dupa refactoring
////
//   private boolean canInsert(FccOpTransactionBo bo) {
//      OPERATOR_PROCESSED_DATA processedData = getLookups().getHasProcessedData();
//      boolean isInPendingPeriod = bo.getOperatorPeriod().isPendingPeriod();
//      boolean hasOtherPendingPeriod = FccOpTransactionBo.hasOtherPendingPeriod(listData, bo);
//
//
////      boolean hasProcessedPrepickInPendingPer = true;
////      if (isInPendingPeriod) {
////         hasProcessedPrepickInPendingPer = getLookups().getHasProcessedPrePickupsInPeriod().isProcessedAllData();
////      }
//
////      boolean hasProcessedPrepickInPendingPer = !isInPendingPeriod ? true : getLookups().getHasProcessedPrePickupsInPeriod().isProcessedAllData();
//
//      boolean hasProcessedPrepickInPendingPer = !isInPendingPeriod  || getLookups().getHasProcessedPrePickupsInPeriod().isProcessedAllData();
//
//      if (!allAccountAreLoaded()) {
//         return false;
//      }
//      if (processedData.isProcessedAllData()) {
//         return (isInPendingPeriod || !hasOtherPendingPeriod);
//      } else {
//         return processedData.isLoggedOnRegister() && isInPendingPeriod && hasProcessedPrepickInPendingPer;
//      }
//   }
//}
