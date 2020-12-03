//package victor.training.pss;
//
//import java.util.Hashtable;
//
//import static java.util.stream.Collectors.toList;
//
//public class Exercitiul2 {
//   TenderBo tenderDefaultBo;
//
//   public int insertDifferencesReal(Hashtable data) {
//      int returnCode = ServerResponse.NO_ERROR;
//      String sqlSync = null;
//      boolean blnDiffRealPosInsertedPending = false;     //set true when exits real diff. pos. saved (for 'negativate' the existent differences) when fcc is made for pending period
//      boolean blnDiffRealNegInsertedPending = false;     //set true when exits real diff. neg. saved (for 'negativate' the existent differences) when fcc is made for pending period
//      TransactionsManager transactionsManager = new TransactionsManager(); //used for get the next available transaction id
//      String signAccountRealPos;
//      String signAccountRealNeg; //sign of account for real differences
//      int groupRealPos;
//      int groupRealNeg;           //group of account for real differences
//      String accountDescrRealPos;
//      String accountDescrRealNeg;     //description of account for real differences
//      String moduleName = null;
//      Hashtable hAccounts = null;
//      AccountBo account = null;
//      try {
//         //get logged operator for insert in MISC_XACT fields: MGR_ID, CHANGE_USER_ID
//         ModuleContext moduleContext = (ModuleContext) data.get(AppConstants.HK_MODULE_CONTEXT);
//         int loggedOpNumber = moduleContext.getOperatorId();
//         moduleName = (String) data.get(CashOfficeFCCMaintConstants.HK_MODULE_NAME);
//         short storeId = ((Short) data.get(CashOfficeFCCMaintConstants.HK_STORE_ID)).shortValue();
//         Vector tenders = (Vector) data.get(CashOfficeFCCMaintConstants.HK_TENDERS);
//         Vector allSql = (Vector) data.get(CashOfficeFCCMaintConstants.HK_SQL);
//         //get curent date, current time formatted for insert in tables
//         String transactionDate = (String) data.get(CashOfficeFCCMaintConstants.HK_TRANSACTION_DATE);
//         String transactionTime = (String) data.get(CashOfficeFCCMaintConstants.HK_TRANSACTION_TIME);
//         Hashtable hAllTransactionId = (Hashtable) data.get(CashOfficeFCCMaintConstants.HK_TRANSACTION_ID);
//         //get period with its status fo know what value for MISC_TYPE to use
//         PeriodBo period = (PeriodBo) data.get(CashOfficeFCCMaintConstants.HK_PERIOD);
//         short periodStatus = period.getStatus();
//         //get accounts: PRM_REAL_CO_DIFF_POS_ACCT, PRM_REAL_CO_DIFF_NEG_ACCT
//         hAccounts = (Hashtable) data.get(CashOfficeFCCMaintConstants.HK_ACCOUNTS);
//         ParameterStoreValue parameterStoreValue = (ParameterStoreValue) hAccounts.get(AppParametersConstants.PRM_REAL_CO_DIFF_POS_ACCT);
//         //5000
//         int accountRealPos = Integer.parseInt(parameterStoreValue.getParamValue());
//         account = (AccountBo) parameterStoreValue.getLinkedData();
//         signAccountRealPos = account.isNegative() ? AppConstants.DB_TRUE_VALUE_1 : AppConstants.DB_FALSE_VALUE_0;
//         groupRealPos = account.getGroupNbr();
//         accountDescrRealPos = account.getDescription();
//         ParameterStoreValue parameterStoreValu = (ParameterStoreValue) hAccounts.get(AppParametersConstants.PRM_REAL_CO_DIFF_NEG_ACCT);
//         //5001
//         //account for real differences positive/negative
//         int accountRealNeg = Integer.parseInt(parameterStoreValu.getParamValue());
//         account = (AccountBo) parameterStoreValu.getLinkedData();
//         signAccountRealNeg = account.isNegative() ? AppConstants.DB_TRUE_VALUE_1 : AppConstants.DB_FALSE_VALUE_0;
//         groupRealNeg = account.getGroupNbr();
//         accountDescrRealNeg = account.getDescription();
//         //get default tender necessary for use its no. of decimlas for transform in integer converted values in local currency
//         //tenderDefaultBo was set when the module was loaded
//         //number of decimals for default tender for storeIDDefault;
//         //get the next available transaction id (get in the order in that they will be inserted; the statistic reports use these id's for order the records)
//         //real diff. pos. transaction Id when fcc is made for a pending period
//         int transactionIdRealPosPending = transactionsManager.getNextTransactionId(moduleName);
//         //real diff. neg. transaction Id fcc is made for a pending period
//         int transactionIdRealNegPending = transactionsManager.getNextTransactionId(moduleName);
//         //real diff. pos. transaction Id
//         int transactionIdRealPos = transactionsManager.getNextTransactionId(moduleName);
//         //real diff. neg. transaction Id
//         int transactionIdRealNeg = transactionsManager.getNextTransactionId(moduleName);
//         hAllTransactionId.put(transactionIdRealPos, new Boolean(false));
//         hAllTransactionId.put(transactionIdRealNeg, new Boolean(false));
//         //if the error code is returned cancel the insert operation
//         if ((transactionIdRealPos == TransactionsManager.TRANSACTION_ID_ERROR) ||
//             (transactionIdRealNeg == TransactionsManager.TRANSACTION_ID_ERROR)) {
//            returnCode = ServerResponse.ERROR;
//            return returnCode;
//         }
//         //A.1. For Pending periods: Save the real differences values in MISC_XACT_TND
//         if (periodStatus == CashOfficeFCCMaintConstants.PERIOD_STATUS_PENDING) {
//            //a.1.1 get positive diff. from ST_CO_TND and insert them whith minus , as a real pos. diff. record
//            String sqlDiffPosPending =
//                "SELECT TND_ID, -SUM(CO_REAL_DIFF_AMT) CO_REAL_DIFF_AMT, -SUM(CO_REAL_DIFF_AMTF) CO_REAL_DIFF_AMTF " +
//                " FROM ST_CO_TND " +
//                " WHERE CO_PERIOD_ID = " + period.getPeriodNumber() + " AND  STORE_ID = " + storeId +
//                " GROUP BY TND_ID " +
//                " HAVING SUM(CO_REAL_DIFF_AMT)>0 OR SUM(CO_REAL_DIFF_AMTF)>0 ";
//            SelectResult selResDiffPosPending = executeSelectSql(sqlDiffPosPending, "");
//            if (selResDiffPosPending != null && selResDiffPosPending.getNumRows() > 0) {
//               for (int p = 0; p < selResDiffPosPending.getNumRows(); p++) {
//                  String sqlMiscXactTnd2 = "INSERT INTO MISC_XACT_TND (" +
//                                   "STORE_ID, XACT_DATE, XACT_TIME, TERM_ID," +
//                                   "XACT_ID, TND_ID, TND_AMT, TND_AMTF, CHANGE_TS ) " +
//                                   "VALUES (" +
//                                   storeId + "," +
//                                   transactionDate + "," +
//                                   transactionTime + "," +
//                                   AppConstants.DB_TERMINAL_ID_BO + "," +
//                                   transactionIdRealPosPending + "," +
//                                   selResDiffPosPending.getShort("TND_ID") + "," +
//                                   //long -> int: get the lowest 4 bytes
//                                   (selResDiffPosPending.getLong("CO_REAL_DIFF_AMT") & 0xFFFF) + "," +
//                                   (selResDiffPosPending.getLong("CO_REAL_DIFF_AMTF") & 0xFFFF) + "," +
//                                   dbTimestamp() +
//                                   ")";
//                  allSql.add(sqlMiscXactTnd2);
//                  selResDiffPosPending.nextRow();
//               }
//               blnDiffRealPosInsertedPending = true;
//            }
//            //a.1.2. get negative diff. from ST_CO_TND and insert them whith minus -as they are!!!-, as a real neg. diff. record
//            String sqlDiffNegPending =
//                "SELECT TND_ID, SUM(CO_REAL_DIFF_AMT) CO_REAL_DIFF_AMT, SUM(CO_REAL_DIFF_AMTF) CO_REAL_DIFF_AMTF " +
//                " FROM ST_CO_TND " +
//                " WHERE CO_PERIOD_ID = " + period.getPeriodNumber() + " AND  STORE_ID = " + storeId +
//                " GROUP BY TND_ID " +
//                " HAVING SUM(CO_REAL_DIFF_AMT)<0 OR SUM(CO_REAL_DIFF_AMTF)<0 ";
//            SelectResult selResDiffNegPending = executeSelectSql(sqlDiffNegPending, "");
//            if (selResDiffNegPending != null && selResDiffNegPending.getNumRows() > 0) {
//               for (int p = 0; p < selResDiffNegPending.getNumRows(); p++) {
//
//                  String sqlMiscXactTnd2 = "INSERT INTO MISC_XACT_TND (" +
//                                   "STORE_ID, XACT_DATE, XACT_TIME, TERM_ID," +
//                                   "XACT_ID, TND_ID, TND_AMT, TND_AMTF, CHANGE_TS ) " +
//                                   "VALUES (" +
//                                   storeId + "," +
//                                   transactionDate + "," +
//                                   transactionTime + "," +
//                                   AppConstants.DB_TERMINAL_ID_BO + "," +
//                                   transactionIdRealNegPending + "," +
//                                   selResDiffNegPending.getShort("TND_ID") + "," +
//                                   selResDiffNegPending.getLong("CO_REAL_DIFF_AMT") + "," +
//                                   selResDiffNegPending.getLong("CO_REAL_DIFF_AMTF") + "," +
//                                   dbTimestamp() +
//                                   ")";
//                  allSql.add(sqlMiscXactTnd2);
//                  selResDiffNegPending.nextRow();
//               }
//               blnDiffRealNegInsertedPending = true;
//            }
//         }
//         // For each tender get values for insert in MISC_XACT_TND
//         //real diff. transaction Id for insert in MISC_XACT,MISC_XACT_TND,SYNC_TLOG ;
//         int transactionIdReal = 0;
//
//
//         boolean blnDiffRealPosInserted = tenders.stream().anyMatch(tender -> getLocalDifference(tender) > 0);
////         boolean blnDiffRealPosInserted = tenders.stream().map(ThinTenderBo::getLocalDifference).anyMatch(diff -> diff > 0);
//
//
//         boolean differentPosNeg = accountRealPos != accountRealNeg;
//
//
//
//
//
//         boolean blnDiffRealNegInserted = false;
//         //set true when exits real diff. saved for know to make the sql for real diff. for MISC_XACT
//
//         for (ThinTenderBo tender : tenders.stream().filter(t -> getLocalDifference(t) != 0).collect(toList())) { // cate? 20
//            transactionIdReal = (getLocalDifference(tender) > 0) ? transactionIdRealPos :  transactionIdRealNeg;
//
//            int differenceLocal = absIfNecessary(differentPosNeg, getLocalDifference(tender));
//
//            int differenceForeign = absIfNecessary(differentPosNeg, getForeignDifference(tender));
//
//
//            TenderDataForQuery dataForQuery = new TenderDataForQuery(
//                tender, storeId,
//                transactionIdReal,
//                differenceLocal, differenceForeign);
//            // SPlit Phase Refactor:
//
//            dataForQuery.method();
//
//            /// de aici in jos va ajunge in Repository
//
//
//            String sqlMiscXactTnd = deMutatInRepo(dataForQuery);
//            allSql.add(sqlMiscXactTnd);
//         } //for each tender
//         //A.2. For a pending period:
//         String sqlMiscXact = null;
//         if (periodStatus == CashOfficeFCCMaintConstants.PERIOD_STATUS_PENDING) {
//            if (blnDiffRealPosInsertedPending) {
//               //A.2.1. Save real diff. pos. values for pending in MISC_XACT: is saved a single record using DB_TYPE_CLOSE_PERIOD for XACT_TYPE
//               sqlMiscXact = "INSERT INTO MISC_XACT (" +
//                             "STORE_ID, XACT_DATE, XACT_TIME, TERM_ID," +
//                             "XACT_ID, MISC_TYPE, OPER_ID, MGR_ID," +
//                             "MISC_ACCT_ID, MISC_NEG_FLAG, MISC_GROUP_ID, REASON_ID, REASON_DESC," +
//                             "XACT_STATUS, CHANGE_USER_ID, CHANGE_TS ) " +
//                             " VALUES (" +
//                             storeId + "," +
//                             transactionDate + "," +
//                             transactionTime + "," +
//                             AppConstants.DB_TERMINAL_ID_BO + "," +
//                             transactionIdRealPosPending + "," +
//                             "'" + AppConstants.DB_MISC_TYPE_CASH_OFFICE_PENDING + "'," +
//                             CashOfficeFCCMaintConstants.OPER_ID + "," +
//                             loggedOpNumber + "," +
//                             accountRealPos + "," +
//                             "'" + signAccountRealPos + "'," +
//                             groupRealPos + "," +
//                             AppConstants.DB_REASON_ID + "," +
//                             "'" + accountDescrRealPos + "'," +
//                             AppConstants.DB_STATUS_TRANSACTION_NOT_PROCESSED + "," +
//                             loggedOpNumber + "," +
//                             dbTimestamp() + ")";
//               allSql.add(sqlMiscXact);
//               //A.3.1. Save real diff. for pending values: in SYNC_TLOG is saved a single record with the same XACT_ID
//               sqlSync = transactionsManager.getSqlForInsertInSyncronizatonTable(
//                   storeId,
//                   transactionDate, transactionTime,
//                   transactionIdRealPosPending,
//                   TransactionsManager.TRANSACTION_TABLE_MISC_XACT);
//               allSql.add(sqlSync);
//            }//blnDiffRealPosInsertedPending
//            //A.2.2. Save real diff. neg. values for pending in MISC_XACT: is saved a single record using DB_TYPE_CLOSE_PERIOD for XACT_TYPE
//            if (blnDiffRealNegInsertedPending) {
//               sqlMiscXact = "INSERT INTO MISC_XACT (" +
//                             "STORE_ID, XACT_DATE, XACT_TIME, TERM_ID," +
//                             "XACT_ID, MISC_TYPE, OPER_ID, MGR_ID," +
//                             "MISC_ACCT_ID, MISC_NEG_FLAG, MISC_GROUP_ID, REASON_ID, REASON_DESC," +
//                             "XACT_STATUS, CHANGE_USER_ID, CHANGE_TS ) " +
//                             " VALUES (" +
//                             storeId + "," +
//                             transactionDate + "," +
//                             transactionTime + "," +
//                             AppConstants.DB_TERMINAL_ID_BO + "," +
//                             transactionIdRealNegPending + "," +
//                             "'" + AppConstants.DB_MISC_TYPE_CASH_OFFICE_PENDING + "'," +
//                             CashOfficeFCCMaintConstants.OPER_ID + "," +
//                             loggedOpNumber + "," +
//                             accountRealNeg + "," +
//                             "'" + signAccountRealNeg + "'," +
//                             groupRealNeg + "," +
//                             AppConstants.DB_REASON_ID + "," +
//                             "'" + accountDescrRealNeg + "'," +
//                             AppConstants.DB_STATUS_TRANSACTION_NOT_PROCESSED + "," +
//                             loggedOpNumber + "," +
//                             dbTimestamp() + ")";
//               allSql.add(sqlMiscXact);
//               //A.3.2. Save real diff. values for pending: in SYNC_TLOG is saved a single record with the same XACT_ID
//               sqlSync = transactionsManager.getSqlForInsertInSyncronizatonTable(
//                   storeId,
//                   transactionDate, transactionTime,
//                   transactionIdRealNegPending,
//                   TransactionsManager.TRANSACTION_TABLE_MISC_XACT);
//               allSql.add(sqlSync);
//            }//blnDiffRealNegInserted
//         }//For a pending period
//         //B.2. Save real diff. values in MISC_XACT: is saved a single record using DB_TYPE_CLOSE_PERIOD for XACT_TYPE
//         if (tenders.stream().anyMatch(t -> getLocalDifference(t) != 0)) {
//            if (blnDiffRealPosInserted) {
//               sqlMiscXact = "INSERT INTO MISC_XACT (" +
//                             "STORE_ID, XACT_DATE, XACT_TIME, TERM_ID," +
//                             "XACT_ID, MISC_TYPE, OPER_ID, MGR_ID," +
//                             "MISC_ACCT_ID, MISC_NEG_FLAG, MISC_GROUP_ID, REASON_ID, REASON_DESC," +
//                             "XACT_STATUS, CHANGE_USER_ID, CHANGE_TS ) " +
//                             " VALUES (" +
//                             storeId + "," +
//                             transactionDate + "," +
//                             transactionTime + "," +
//                             AppConstants.DB_TERMINAL_ID_BO + "," +
//                             transactionIdRealPos + ",";
//               if (periodStatus == CashOfficeFCCMaintConstants.PERIOD_STATUS_OPENED) {
//                  sqlMiscXact += "'" + AppConstants.DB_MISC_TYPE_CASH_OFFICE + "',";
//               } else {//CashOfficeFCCMaintConstants.PERIOD_STATUS_PENDING
//                  sqlMiscXact += "'" + AppConstants.DB_MISC_TYPE_CASH_OFFICE_PENDING + "',";
//               }
//               sqlMiscXact +=
//                   CashOfficeFCCMaintConstants.OPER_ID + "," +
//                   loggedOpNumber + "," +
//                   accountRealPos + "," +
//                   "'" + signAccountRealPos + "'," +
//                   groupRealPos + "," +
//                   AppConstants.DB_REASON_ID + "," +
//                   "'" + accountDescrRealPos + "'," +
//                   AppConstants.DB_STATUS_TRANSACTION_NOT_PROCESSED + "," +
//                   loggedOpNumber + "," +
//                   dbTimestamp() + ")";
//               allSql.add(sqlMiscXact);
//               //B.3. Save real diff. values: in SYNC_TLOG is saved a single record with the same XACT_ID
//               sqlSync = transactionsManager.getSqlForInsertInSyncronizatonTable(
//                   storeId,
//                   transactionDate, transactionTime,
//                   transactionIdRealPos,
//                   TransactionsManager.TRANSACTION_TABLE_MISC_XACT);
//               allSql.add(sqlSync);
//            }//blnDiffRealPosInserted
//            if (blnDiffRealNegInserted) {
//               sqlMiscXact = "INSERT INTO MISC_XACT (" +
//                             "STORE_ID, XACT_DATE, XACT_TIME, TERM_ID," +
//                             "XACT_ID, MISC_TYPE, OPER_ID, MGR_ID," +
//                             "MISC_ACCT_ID, MISC_NEG_FLAG, MISC_GROUP_ID, REASON_ID, REASON_DESC," +
//                             "XACT_STATUS, CHANGE_USER_ID, CHANGE_TS ) " +
//                             " VALUES (" +
//                             storeId + "," +
//                             transactionDate + "," +
//                             transactionTime + "," +
//                             AppConstants.DB_TERMINAL_ID_BO + "," +
//                             transactionIdRealNeg + ",";
//               if (periodStatus == CashOfficeFCCMaintConstants.PERIOD_STATUS_OPENED) {
//                  sqlMiscXact += "'" + AppConstants.DB_MISC_TYPE_CASH_OFFICE + "',";
//               } else {//CashOfficeFCCMaintConstants.PERIOD_STATUS_PENDING
//                  sqlMiscXact += "'" + AppConstants.DB_MISC_TYPE_CASH_OFFICE_PENDING + "',";
//               }
//               sqlMiscXact +=
//                   CashOfficeFCCMaintConstants.OPER_ID + "," +
//                   loggedOpNumber + "," +
//                   accountRealNeg + "," +
//                   "'" + signAccountRealNeg + "'," +
//                   groupRealNeg + "," +
//                   AppConstants.DB_REASON_ID + "," +
//                   "'" + accountDescrRealNeg + "'," +
//                   AppConstants.DB_STATUS_TRANSACTION_NOT_PROCESSED + "," +
//                   loggedOpNumber + "," +
//                   dbTimestamp() + ")";
//               allSql.add(sqlMiscXact);
//               //B.3. Save real diff. values: in SYNC_TLOG is saved a single record with the same XACT_ID
//               sqlSync = transactionsManager.getSqlForInsertInSyncronizatonTable(
//                   storeId,
//                   transactionDate, transactionTime,
//                   transactionIdRealNeg,
//                   TransactionsManager.TRANSACTION_TABLE_MISC_XACT);
//               allSql.add(sqlSync);
//            }//blnDiffRealNegInserted
//         }//blnDiffRealInserted
//      } catch (Exception e) {
//         logMessage(LogManager.EXCEPTION, "insertDifferencesReal()", "Exc: " + e);
//         returnCode = ServerResponse.EXC_UNKNOWN;
//      }
//      return returnCode;
//   }
//
//   private String deMutatInRepo(TenderDataForQuery dataForQuery) {
//      String sqlMiscXactTnd = "INSERT INTO MISC_XACT_TND (" +
//                              "STORE_ID, XACT_DATE, XACT_TIME, TERM_ID," +
//                              "XACT_ID, TND_ID, TND_AMT, TND_AMTF, CHANGE_TS ) " +
//                              "VALUES (" +
//                              dataForQuery.getStoreId() + "," +
//                              AppConstants.DB_TERMINAL_ID_BO + "," +
//                              dataForQuery. getTransactionIdReal() + "," +
//                              dataForQuery.getTenderNumber() + "," +
//                              dataForQuery.getDifferenceLocal() + "," +
//                              dataForQuery.getDifferenceForeign() + "," +
//                              dbTimestamp() +
//                              ")";
//      return sqlMiscXactTnd;
//   }
//
//   private int absIfNecessary(boolean differentPosNeg, int localDifference) {
//      int differenceLocal;
//      if (differentPosNeg) {
//         differenceLocal = Math.abs(localDifference);
//      } else {
//         differenceLocal = localDifference;
//      }
//      return differenceLocal;
//   }
//
//   private int getForeignDifference(ThinTenderBo tender) {
//      return Converter.vsDoubleToInt(tender.getDifferenceForeign(), tender.getNumbDec());
//   }
//
//   private int getLocalDifference(ThinTenderBo tender) {
//      if (tender.isForeign()) {
//         //for foreign tenders, for local values use no. of decimals for default tender
//         return Converter.vsDoubleToInt(tender.getDifferenceLocal(), tenderDefaultBo.getNumbDec());
//      } else {
//         //for foreign tenders, for local values use no. of decimals of current tender
//         return Converter.vsDoubleToInt(tender.getDifferenceLocal(), tender.getNumbDec());
//      }
//   }
//}
