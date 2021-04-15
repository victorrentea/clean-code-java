package victor.training.sample.maintanance;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.logging.Logger;

@Stateless
public class SaveContract {

	@Inject ContractStore contractStore;
	@Inject ContractPaymentDetailsStore contractPaymentDetailsStore;
	@Inject BankAccountStore bankAccountStore;
	@Inject StoVertragRequestCreator saveContractRequestConverter;
	@Inject SaveContractResponseCreator saveContractResponseCreator;
	@Inject	TranslationFactory translationFactory;
	@Core
	@Inject
	CurrentUser currentUser;

	@Inject	DocumentStore documentStore;

	// TODO: add validations
	public SaveContractResponse execute(SaveContractRequest saveContractRequest) throws Exception {
		SaveContractResponse saveContractResponse = null;
		Translation translation = translationFactory.createFor(currentUser.getWorkingLocale());

		try {
			StoVertragRequest stoVertragRequest = saveContractRequestConverter.convert(saveContractRequest);

			if (stoVertragRequest.getContractNumber() != null && stoVertragRequest.getContractNumber() != 0 && saveContractRequest.getContract().getCustomerBankAccountId() == 0) {
				Integer tenantId = stoVertragRequest.getTenantId();
				Integer companyNumber = stoVertragRequest.getCompanyNumber();
				Integer contractNumber = stoVertragRequest.getContractNumber();
				Contract contract = contractStore.getBy(tenantId, companyNumber, contractNumber);
				refreshPaymentDetails(contract,saveContractRequest.getContract().getPaymentTypeId());
			}

			StoVertragResponse stoVertragResponse = contractStore.save(stoVertragRequest);
			Integer tenantId = stoVertragResponse.getTenantId();
			Integer companyNumber = stoVertragResponse.getCompanyNumber();
			Integer contractNumber = stoVertragResponse.getContractNumber();
			Contract contract = contractStore.getBy(tenantId,companyNumber,contractNumber);
			try {
				contractStore.refresh(contract);
			}
			catch(Exception e) {
				// For Porsche contract service, we sometimes get "Entity not managed" exception. Log is added to try to locate problem.
				Logger.getLogger(SaveContract.class.getName()).severe("ERROR in SaveContract usecase, on contract refresh!");
				Logger.getLogger(SaveContract.class.getName()).severe("stoVertragRequest: " + stoVertragRequest.toString());
				Logger.getLogger(SaveContract.class.getName()).severe("stoVertragResponse: " + stoVertragResponse.toString());
				Logger.getLogger(SaveContract.class.getName()).severe("contract: " + contract.toString());
				e.printStackTrace();
				throw e;
			}
			if (isContractSaved(stoVertragResponse) && isTireProduct(saveContractRequest))
				connectTireDocumentWithContract(saveContractRequest.getContract().getTireDocumentId(), contract);
			saveContractResponse = saveContractResponseCreator.convert(stoVertragResponse);
		} catch (ThirdPartyServiceCommunicationException e) {
			saveContractResponse = new SaveContractResponse();
			String error = translation.getText("message", "errorCalculatingMaintenancePremium");
			saveContractResponse.setValidationError(error);
			return saveContractResponse;
		}
		return saveContractResponse;
	}

	private boolean isContractSaved(StoVertragResponse stoVertragResponse) {
		return stoVertragResponse.getContractNumber()!=0;
	}

	private boolean isTireProduct(SaveContractRequest saveContractRequest) {
		// TODO: maybe this should be checked by warranty program type?
		return saveContractRequest.getContract().getTireDocumentId()!=0;
	}

	private void connectTireDocumentWithContract(Long tireDocumentId, Contract contract) {
		if (tireDocumentId==0 || contract.isNull())
			return;

		Document document = documentStore.getById(tireDocumentId);
		if (document.isNull())
			return;

		documentStore.connectDocumentWithContract(document, contract);
	}

	public void refreshPaymentDetails(Contract contract, Integer paymentTypeId) throws ApplicationException {
		if(contract != null && ContractStatusUtil.isOpen(contract) && !contract.getContractPaymentDetails().isNull()){
			if(contract.getContractPaymentDetails().getBankAccount().getId() != null)
				bankAccountStore.markAsDeleted(contract.getContractPaymentDetails().getBankAccount().getId());
			if(paymentTypeId != null && paymentTypeId > 0 )
				contractPaymentDetailsStore.removeBankAccount(contract.getContractPaymentDetails().getId(),paymentTypeId);
			else
				contractPaymentDetailsStore.delete(contract.getContractPaymentDetails().getId());
		}
	}
}