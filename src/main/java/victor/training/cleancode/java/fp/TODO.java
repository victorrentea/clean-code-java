package victor.training.cleancode.java.fp;

public class TODO {
//private void cancelOrRejectExtension(AppleNotificationPayload payload) {
//    List<ContractDetails> contractDetailsList = payload.getContractList().stream()
//            .map(contract -> {
//                String contractId = contract.getContractID();
//                String systemName = contract.getSystemName();
//                String networkNodeId = payload.getNetworkNodeId();
//                String customerNo = appleRepository.getCustomerNo(contractId, systemName);
//                List<String> changeIds = appleRepository.getFtthServiceOrderChangeId(contractId, systemName, networkNodeId);
//                ContractDetails details = new ContractDetails();
//                details.setCustomerName(appleRepository.getCustomerName(customerNo));
//                details.setCustomerNo(customerNo);
//                details.setMsisdnDest(appleRepository.getDealerMsisdn(contractId, systemName));
//                details.setChangeIds(changeIds);
//                appleRepository.cancelChange(changeIds);
//                appleRepository.cancelChangeItem(changeIds);
//                return details;
//            })
//            .collect(Collectors.toList());
//
//    contractDetailsList.forEach(details -> {
//        for (String changeId : details.getChangeIds()) {
//            String smsMessage = String.format(MESSAGE_TO_BE_SENT, changeId, details.getCustomerName(), details.getCustomerNo());
//            sendSmsRepository.sendSms(details.getMsisdnDest(), smsMessage);
//        }
//    });
}

