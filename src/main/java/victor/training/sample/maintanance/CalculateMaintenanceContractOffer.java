package victor.training.sample.maintanance;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class CalculateMaintenanceContractOffer {

    @Inject
    GetWarrantyProgram getWarrantyProgram;
    @Inject
    MaintenanceContractOfferStore store;
    @Inject
    MaintenanceContractOffers offers;
    @Inject
    GetPartner getPartner;
    @Inject @Core
    CurrentUser currentUser;
    @Inject SaveMaintenancePlan saveMaintenancePlan;
    @Inject MaintenancePlanStore maintenancePlanStore;

    public void execute(GetMaintenancePlanRequest maintenancePlanRequest) throws Exception {
        Partner partner = getPartner.execute(currentUser.getWorkingTenantId(), currentUser.getWorkingCompanyId(), currentUser.getWorkingDealerId());
        offers.add(maintenancePlanRequest.getSessionId(), calculate(maintenancePlanRequest, partner));
    }

    public MaintenanceContractOffer calculate(GetMaintenancePlanRequest maintenancePlanRequest, Partner partner) throws Exception {
        WarrantyProgram warrantyProgram = getWarrantyProgram.byIdForSpecificPartner(partner.getId(), maintenancePlanRequest.getWarrantyProgramId());

        validate(warrantyProgram.getAmendment());

        maintenancePlanRequest.setOilPrice(warrantyProgram.getAmendment().getMaintenanceOilPrice());
        maintenancePlanRequest.setWagePrice(warrantyProgram.getAmendment().getMaintenanceWagePrice());
        maintenancePlanRequest.setMaterialPartPercent(warrantyProgram.getAmendment().getMaterialPartPercent());
        maintenancePlanRequest.setWorkHour(warrantyProgram.getAmendment().getWorkHour());

        SaveMaintenancePlanResponse maintenancePlanResponse = saveMaintenancePlan.execute(maintenancePlanRequest);

        MaintenanceContractOffer offer = init(maintenancePlanRequest, maintenancePlanResponse, warrantyProgram, partner);
        offer.setNumberOfMaintenance(maintenancePlanStore.getByMaintenancePlanDocumentId(offer.getDocId()).getNumberOfMaintenance());
        store.create(offer);
        offer.setMonthlyCost(offer.getGrossPrice().divide(BigDecimal.valueOf(offer.getPeriod()), 2, RoundingMode.HALF_UP));
        return offer;
    }

    private MaintenanceContractOffer init(GetMaintenancePlanRequest maintenancePlanRequest,
                                          GetMaintenancePlanResponse maintenancePlanResponse,
                                          WarrantyProgram warrantyProgram,
                                          Partner partner) {
        MaintenanceContractOffer offer = new MaintenanceContractOffer();
        offer.setTenantId(partner.getTenantId());
        offer.setCompanyNumber(partner.getCompanyNumber());
        offer.setDealerNumber(partner.getDealerNumber());
        offer.setMakeGroup(warrantyProgram.getMakeGroup());
        offer.setKind(warrantyProgram.getKind());
        offer.setProduct(warrantyProgram.getProduct());
        offer.setProductName(warrantyProgram.getProductName() + " - " + warrantyProgram.getKind() + " (" + warrantyProgram.getProduct() + ")");
        offer.setValidFrom(warrantyProgram.getValidFrom());
        offer.setPeriod(maintenancePlanRequest.getMonthTo() - maintenancePlanRequest.getMonthFrom());
        offer.setMileage(maintenancePlanRequest.getMileageTo() - maintenancePlanRequest.getMileageFrom());
        offer.setOilQuantity(maintenancePlanResponse.getOilQuantity());
        offer.setWorkHours(maintenancePlanResponse.getWorkHours());
        offer.setPartsCost(maintenancePlanResponse.getPartsCost());
        offer.setDocId(maintenancePlanResponse.getDocId());
        offer.setModules(maintenancePlanRequest.getModules());
        offer.setModulesName(maintenancePlanRequest.getModulesName().split(";"));
        offer.setFirstRegistration(maintenancePlanRequest.getFirstRegistration());
        offer.setMileageFrom(maintenancePlanRequest.getMileageFrom());
        offer.setVehicleCode(maintenancePlanRequest.getVehicleCode());
        offer.setWarrantyProgramId(maintenancePlanRequest.getWarrantyProgramId());
        return offer;
    }

    private void validate(Amendment amendment) throws ApplicationException {
        List<String> messages = new ArrayList<>();
        List<String> nonValidFields = new ArrayList<>();

        if (amendment.getMaintenanceOilPrice() == null || amendment.getMaintenanceOilPrice() == 0) {
            messages.add("Oil price is not set!");
            nonValidFields.add("oilPrice");
        }

        if (amendment.getMaintenanceWagePrice() == null || amendment.getMaintenanceWagePrice() == 0) {
            messages.add("Wage price is not set!");
            nonValidFields.add("wagePrice");
        }

        if (!nonValidFields.isEmpty())
            throw new ApplicationException(460, messages, nonValidFields);
    }
}
