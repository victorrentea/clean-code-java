package victor.training.cleancode.fp;

public class TODO {
//private void cancelOrRejectExtension(FtthdNotificationPayload payload) {
//    List<ContractDetails> contractDetailsList = payload.getContractList().stream()
//            .map(contract -> {
//                String contractId = contract.getContractID();
//                String systemName = contract.getSystemName();
//                String networkNodeId = payload.getNetworkNodeId();
//                String customerNo = ftthdRepository.getCustomerNo(contractId, systemName);
//                List<String> changeIds = ftthdRepository.getFtthServiceOrderChangeId(contractId, systemName, networkNodeId);
//                ContractDetails details = new ContractDetails();
//                details.setCustomerName(ftthdRepository.getCustomerName(customerNo));
//                details.setCustomerNo(customerNo);
//                details.setMsisdnDest(ftthdRepository.getDealerMsisdn(contractId, systemName));
//                details.setChangeIds(changeIds);
//                //  ftthdRepository.cancelOfferActivationChange(contractId, systemName, networkNodeId);
//                ftthdRepository.cancelChange(changeIds);
//                ftthdRepository.cancelChangeItem(changeIds);
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

