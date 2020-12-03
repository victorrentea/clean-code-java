//package victor.training.pss;
//
//import javax.swing.*;
//
//public class Exercitiu3 {
//}
//
// class CashOfficeFCCMaintPanel extends
//     TransactionMaintPanel {
//   private pss.kernel.gui.controls.PssPanel pnlDetails = null;  //  @jve:visual-info  decl-index=0 visual-constraint="32,30"
//   private pss.kernel.gui.controls.PssLabel lblDetailsHeader = null;
//   private pss.kernel.gui.controls.PssPanel pnlDetailsControls = null;
//   private pss.kernel.gui.controls.PssScrollPane scrlTenders = null;
//   private pss.kernel.gui.controls.PssTableControl tblTenders = null;
//   private PssButton btnExpand = null;          //BTN_USER1
//   private PssButton btnCountMoney = null; //BTN_USER2
//   private PssButton btnExitAndSave = null; //BTN_USER3
//   private PssButton btnPending = null;         //BTN_USER4
//   private PssButton btnFCC = null;        //BTN_INSERT
//   //default tender (with tenderNUmber= AppConstants.TENDER_NUMBER_DEFAULT) for a store;
//   //is set every time when the tenders table is filled with tenders for a specific store
//   //with the default tender for that store
//   private ThinTenderBo defaultTender;
//   //module parameters corresponding to selected store; is set every time when the store from loader is changed
//   private Hashtable hDbParameters;
//   //Hastable with explanations for existent additional rows in table
//   // key: tender number; value: the index of additional row
//   private Hashtable hTendersRowIndexesExpl;
//   //Vector with indexes of rows in tenders table that not participate on total:
//   //foreign tenders rows, total row, groups rows
//   private Vector vctRowIndexes;
//   //Vector with indexes of groups rows in tenders table
//   private Vector vctRowIndexesGroups;
//   //the index of total row in table (now is the last row)
//   private int rowIndexTotal;
//   private Hashtable hTendersValues;     //an image of tblTenders with all possible rows and columns
//   //key: index from Model; value TenderBo object
//   //if show add rows: key = index in table
//   //if not show add rows: key != index in table
//   private Hashtable hRowsIndexes;       //contains all rows from tblTenders as is shown on screen
//   //key: row in table ; value:index in hTendersValues
//   //keep the status of expanding/collapsing columns: true if are expended; false if are collapsed
//   //on loading the module, are expanded (set true on initView)
//   private boolean isExpanded;
//   //columns that are expanded/removed; set in these members for add them in table after there are removed
//   TableColumn colBank;
//   TableColumn colMiscIn;
//   TableColumn colMiscOut;
//   TableColumn colFccOper;
//   TableColumn colPickup;
//   TableColumn colLoan;
//   TableColumn colBalance;
//   TableColumn colCashCount;
//   TableColumn colCashCountShouldBe;
//   TableColumn colDifference;
//   TableColumn colDifferenceExch;
//   private boolean useDecimalSymbol; //the value of PRM_USE_DECIMAL_SYMBOL
//   private boolean showAddRows;          //the value of PRM_SHOW_ADD_ROWS_CO
//   private boolean showDiffExchColumn;   //the value of PRM_SHOW_DIFF_EXCH_CO
//   private short printMethodFCC;              //the value of PRM_PRINT_METHOD_FCC_CO
//   private pss.kernel.gui.controls.PssPanel pnlPeriod = null;
//   private pss.kernel.gui.controls.PssLabel lblPeriod = null;
//   private pss.kernel.gui.controls.PssLabel lblPeriodDetails = null;
//   /**
//    * set when loaded as FCC, if exist operators in pending for selected period in enableButtonsDependingByConditions() method
//    * (show the message in fillControls(), because when disable the button in enableButtonsDependingByConditions()
//    * the table is not filled and the message window appears when the table has only the header, and after ok on messaje the table is filled
//    * if the message is by type time_out, it appears just few seconnds and cannot be read)
//    */
//   private boolean showMsgforOperatorsInPending;
//   AppResources appResource = null;
//
//   public void method() {
//      if (ceva) {
//         updateValuesTendersTable(1,2, 1.0);
//      } else {
//         updateValuesTendersTable(1,2, 3.0);
//      }
//   }
//   private void updateValuesTendersTable(int row, int col, double differenceExch) {
//      try {
//         Vector vctStoreNormalTenders = getModelTenders();
//         int index = (Integer) hRowsIndexes.get(row);
//         ThinTenderBo boTender = (ThinTenderBo) vctStoreNormalTenders.elementAt(index);
//         // read from COL_CASH_COUNT, current row
//         double cashCount = escapeMinusZero(row, col);
//
//
//         String cashCountShouldBeStr = getTblTenders().getValueAt(row, getColumnIndex(CashOfficeFCCMaintConstants.COL_CASH_COUNT_SHOULD_BE)).toString();
//         double cashCountShouldBe = AppUtilities.getDoubleValue(cashCountShouldBeStr);
//
//         double difference = - (cashCountShouldBe - cashCount);  // <<<<<<<<<<<<<<<< AFARA
//         double differenceRounded = Utilities.getRoundedDouble(difference, boTender.getNumbDec()); //correct double value obtained if is not exactly the correct result
//         String strDifference = LocaleUtilities.convertDoubleToString(differenceRounded, boTender.getNumbDec());
//         updateTblTendersRow(row, strDifference, CashOfficeFCCMaintConstants.COL_DIFFERENCE);
//
//         //if is a foreign tender: calc. additional values ;
//         // update the hTendersValues, the additional row (if is shown), the exch col (if is shown)
//         // read from COL_CASH_COUNT, additional row
//         double cashCountConv = 0;
//         // COL_DIFFERENCE, additional row
//         double differenceConv = 0;
//         //COL_DIFFERENCE_EXCH, additional row
//         if (boTender.isForeign()) {
//            //calculate cashCount value converted in local currency for additional row
//            int rowAdditional = ((Integer) hTendersRowIndexesExpl.get(new Short(boTender.getNumber()))).intValue();
//            cashCountConv = AppUtilities.convertForeignToLocal(boTender, defaultTender, cashCount);
//            if (showAddRows) {
//               String strCashCountConv = LocaleUtilities.convertDoubleToString(cashCountConv, defaultTender.getNumbDec());
//               if (isExpanded) { //COL_CASH_COUNT have its real index
//                  getTblTenders().setValueAt(strCashCountConv, rowAdditional, CashOfficeFCCMaintConstants.COL_CASH_COUNT);
//               } else { //COL_CASH_COUNT is shifted left with NO_COLUMNS_COLLAPSED
//                  getTblTenders().setValueAt(strCashCountConv, rowAdditional, CashOfficeFCCMaintConstants.COL_CASH_COUNT - CashOfficeFCCMaintConstants.NO_COLUMNS_COLLASPED);
//               }
//            }
//            //calculate difference value converted in local currency for additional row
//            differenceConv = AppUtilities.convertForeignToLocal(boTender, defaultTender, differenceRounded);
//            differenceConv = Utilities.getRoundedDouble(differenceConv, defaultTender.getNumbDec());
//            if (showAddRows) {
//               String strDifferenceConv = LocaleUtilities.convertDoubleToString(differenceConv, defaultTender.getNumbDec());
//               updateTblTendersRow(rowAdditional, strDifferenceConv, CashOfficeFCCMaintConstants.COL_DIFFERENCE);
//            }
//            //calculate exchange differences for additional row:
//            // Exchange differences = -(CashCountShouldBeFconv - CashCountFconv) - DiffRealConv
//            // read from COL_CASH_COUNT_SHOULD_BE, additional row
//            double cashCountShouldBeConv = boTender.getCashCountShouldBe();
//            if ("1" =="a") {
//               differenceExch = -cashCountShouldBeConv + cashCountConv - differenceConv; // <<<<<<<<<<<<<<<< AFARA
//            } else {
//               differenceExch = Utilities.getRoundedDouble(differenceExch, defaultTender.getNumbDec()); //correct double value obtained if is not exactly the correct result
//            }
//            if (showDiffExchColumn && showAddRows) { //cell for additional Row and DiffExchColumn
//               String strDifferenceExch = LocaleUtilities.convertDoubleToString(differenceExch, defaultTender.getNumbDec());
//               updateTblTendersRow(rowAdditional, strDifferenceExch, CashOfficeFCCMaintConstants.COL_DIFFERENCE_EXCH);
//            }
//         }
//         //Put all these values also in hTendersValues
//         updateValueshTenders(row, cashCount, cashCountConv, differenceRounded, differenceConv, differenceExch);
//         //recalculate the total in local currency for all tenders
//         updateTotalForTenders();
//      } catch (Exception e) {
//         logMessage(LogManager.EXCEPTION, "updateValuesTendersTable()", e); //$NON-NLS-1$
//      }
//   }
//
//    private double escapeMinusZero(int row, int col) {
//       double cashCount = AppUtilities.getDoubleValue(getTblTenders().getValueAt(row, col).toString());
//       //replace -0 with 0 in cell
//       if (cashCount == -0) {
//          getTblTenders().setValueAt((double) 0, row, col);
//       }
//       return cashCount;
//    }
//
//    private JTable getTblTenders() {
//       return null;
//    }
//
//    private void updateTblTendersRow(int rowAdditional, String strDifferenceExch, int originalColumnIndex) {
//       getTblTenders().setValueAt(strDifferenceExch, rowAdditional, getColumnIndex(originalColumnIndex));
//    }
//
//    private int getColumnIndex(int originalColumnIndex) {
//       if (isExpanded) {
//          return originalColumnIndex;
//       } else {
//          return originalColumnIndex - CashOfficeFCCMaintConstants.NO_COLUMNS_COLLAPSED;
//       }
//    }
//
// }
