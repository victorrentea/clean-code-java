//package com.realgarant.wms.business.multicontract.usecases.savemulticontract;
//
//import com.realgarant.wms.business.CurrentUser;
//import com.realgarant.wms.business.contract.usecases.savecontract.SaveContract;
//import com.realgarant.wms.business.contract.usecases.savecontract.request.SaveContractRequest;
//import com.realgarant.wms.business.contract.usecases.savecontract.request.SaveWarrantyRequest;
//import com.realgarant.wms.business.contract.usecases.savecontract.response.SaveContractResponse;
//import com.realgarant.wms.business.multicontract.MultiContractStore;
//import com.realgarant.wms.business.multicontract.usecases.GetMultiContract;
//import com.realgarant.wms.business.multicontract.usecases.printmulticontract.PrintMultiContract;
//import com.realgarant.wms.business.multicontract.usecases.savemulticontract.validation.SaveMultiContractValidator;
//import com.realgarant.wms.utilities.translations.Translation;
//import com.realgarant.wms.utilities.translations.TranslationFactory;
//import com.realgarant.wms.utilities.annotations.qualifiers.Core;
//import com.realgarant.wms.utilities.exceptions.ValidationException;
//
//import javax.ejb.Stateless;
//import javax.inject.Inject;
//import java.util.ArrayList;
//import java.util.List;
//
//@Stateless
//public class SaveMultiContract {
//
//	@Inject @Core CurrentUser currentUser;
//	@Inject GetMultiContract getMultiContract;
//	@Inject	SaveContract saveContract;
//	@Inject MultiContractStore multiContractStore;
//	@Inject SaveMultiContractResponseCreator saveMultiContractResponseCreator;
//	@Inject	SaveMultiContractValidator saveMultiContractValidator;
//	@Inject	TranslationFactory translationFactory;
//	@Inject PrintMultiContract printMultiContract;
//
//	public SaveMultiContractResponse execute(SaveMultiContractRequest saveMultiContractRequest) throws Exception {
//		Translation translation = translationFactory.createFor(currentUser.getWorkingLocale());
//		List<String> validationMessages = saveMultiContractValidator.validate(saveMultiContractRequest, translation);
//
//		if (!validationMessages.isEmpty()) {
//			throw new ValidationException(validationMessages);
//		}
//
//		Integer multiContractId = saveMultiContractRequest.getId();
//		if (multiContractId == null || multiContractId == 0) {
//			multiContractId = multiContractStore.generateId();
//			saveMultiContractRequest.setId(multiContractId);
//		}
//
//		// // TODO: 7/13/2017
//		// later we will use same code for saving simple and mutli contract, stovertragrequests list size will determin should we save simple or multicontract
//		// for now leave as it is since we are trying to save one contract as multicontract in order to finish integration
//		// since stovertrag now is getting multigruupid as parameter, in case of simple contract set 0 to that parameter
//
//		List<SaveWarrantyRequest> warrantyRequests = getContractsForSaving(saveMultiContractRequest);
//
//		List<SaveContractRequest> saveContractRequests = new ArrayList<>();
//		for (SaveWarrantyRequest warrantyRequest : warrantyRequests) {
//			SaveContractRequest saveContractRequest = new SaveContractRequest();
//			saveContractRequest.setId(warrantyRequest.getContractNumber());
//			saveContractRequest.setMultiContractId(multiContractId);
//			saveContractRequest.setCustomer(saveMultiContractRequest.getCustomer());
//			saveContractRequest.setCustomerVehicle(saveMultiContractRequest.getCustomerVehicle());
//			saveContractRequest.setVehicle(saveMultiContractRequest.getVehicle());
//			saveContractRequest.setContract(warrantyRequest);
//			saveContractRequest.setChangeReason(saveMultiContractRequest.getChangeReason());
//			saveContractRequest.setChangeDescription(saveMultiContractRequest.getChangeDescription());
//			saveContractRequests.add(saveContractRequest);
//		}
//
//		List<SaveContractResponse> saveContractResponses = new ArrayList<>();
//		for (SaveContractRequest saveContractRequest : saveContractRequests) {
//			SaveContractResponse saveContractResponse = saveContract.execute(saveContractRequest);
//			saveContractResponses.add(saveContractResponse);
//
//		}
//
//		SaveMultiContractResponse saveMultiContractResponse;
//		Integer createdMultiContractId = 0;
//		if (!getMultiContract.byId(multiContractId).isEmpty())
//			createdMultiContractId = multiContractId;
//
//		saveMultiContractResponse = saveMultiContractResponseCreator.create(createdMultiContractId, saveContractResponses);
//
//		return saveMultiContractResponse;
//	}
//
//	private List<SaveWarrantyRequest> getContractsForSaving(SaveMultiContractRequest saveMultiContractRequest) {
//		List<SaveWarrantyRequest> allContracts = saveMultiContractRequest.getContracts();
//		List saveContractsFilter = saveMultiContractRequest.getSaveContractsFilter();
//		if (saveContractsFilter.isEmpty())
//			return allContracts;
//
//		List<SaveWarrantyRequest> contractsForSave = new ArrayList<>();
//		for (SaveWarrantyRequest contract : allContracts) {
//			if (saveContractsFilter.contains(contract.getContractNumber()))
//				contractsForSave.add(contract);
//		}
//		return contractsForSave;
//	}
//
//	public SaveMultiContractResponse saveAndPrint(SaveMultiContractRequest saveMultiContractRequest) throws Exception {
//		SaveMultiContractResponse res = this.execute(saveMultiContractRequest);
//		printMultiContract.execute(res.getMultiContractId());
//		return res;
//	}
//}