package victor.training.sample.maintanance;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static java.math.RoundingMode.HALF_UP;

public class CalculateMaintenanceContractOffer {

    @Inject
    GetWarrantyProgram getWarrantyProgram; // usecase
    @Inject
    MaintenanceContractOfferStore store; // ~ REPOSITORY
    @Inject
    MaintenanceContractOffers offers; // ojbect storing data (mutates) stored on session
    @Inject
    GetPartner getPartner; // usecase
    @Inject @Core
    CurrentUser currentUser;
    @Inject SaveMaintenancePlan saveMaintenancePlan; //usecases
    @Inject MaintenancePlanStore maintenancePlanStore;//repo

    public void execute(GetMaintenancePlanRequest maintenancePlanRequest) throws Exception {
        Partner partner = getPartner.execute(currentUser.getWorkingTenantId(), currentUser.getWorkingCompanyId(), currentUser.getWorkingDealerId());
        // 1 can't these move to the getPartner.execute()
        // 2 cant' you pass the entire CurrentUser
//        Partner partner = getPartner.execute(currentUser);

//        String s="a", b="a";
        offers.add(maintenancePlanRequest.getSessionId(), calculate(maintenancePlanRequest, partner));
    }

    public MaintenanceContractOffer calculate(GetMaintenancePlanRequest maintenancePlanRequest, Partner partner) throws Exception {
        WarrantyProgram warrantyProgram = getWarrantyProgram.byIdForSpecificPartner(partner.getId(), maintenancePlanRequest.getWarrantyProgramId());

        validate(warrantyProgram.getAmendment());

        GetMaintenancePlanRequestWarranted warranted = new GetMaintenancePlanRequestWarranted(maintenancePlanRequest, warrantyProgram);


        maintenancePlanRequest.setOilPrice(warrantyProgram.getAmendment().getMaintenanceOilPrice());
        maintenancePlanRequest.setWagePrice(warrantyProgram.getAmendment().getMaintenanceWagePrice());
        maintenancePlanRequest.setMaterialPartPercent(warrantyProgram.getAmendment().getMaterialPartPercent());
        maintenancePlanRequest.setWorkHour(warrantyProgram.getAmendment().getWorkHour());

        SaveMaintenancePlanResponse maintenancePlanResponse = saveMaintenancePlan.execute(maintenancePlanRequest);
        // TODO (est 6 mo work) separate conversion from API models (...Request and ... Response objects). Keep it out,
        //  peripheral to your central biz logic that you might want to reuse.

        // in other words, whenever you want to call some existing domain logic, make sure you talk (send/return ) only
        // data structures that are 'private' internal to your application, not some API models that your REST clients "controls"

        MaintenanceContractOffer offer = new MaintenanceContractOffer(maintenancePlanRequest, maintenancePlanResponse, warrantyProgram, partner);
        offer.setNumberOfMaintenance(maintenancePlanStore.getByMaintenancePlanDocumentId(offer.getDocId()).getNumberOfMaintenance());
        store.create(offer);
        return offer;
    }


    private void validate(Amendment amendment) throws ApplicationException {
        List<String> messages = new ArrayList<>();
        List<String> nonValidFields = new ArrayList<>();

        // may be room for an util for != && != 0
        if (positive(amendment.getMaintenanceOilPrice())) {
            messages.add("Oil price is not set!");
            nonValidFields.add("oilPrice"); // to keep in sync with the REST api model ! OMG!! "OMG!!!
//            WarrantyProgramFields.oilPrice << enum
//            WarrantyProgram.getOilPrice();
        }

        if (positive(amendment.getMaintenanceWagePrice())) {
            messages.add("Wage price is not set!");
            nonValidFields.add("wagePrice");
        }


//        Validator validator;
//        Set<ConstraintViolation<Amendment>> validate = validator.validate(amendment);
//        for (ConstraintViolation<Amendment> violation : validate) {
//            violation.get
//        }
        if (!nonValidFields.isEmpty())
            throw new ApplicationException(460, messages, nonValidFields);
    }

    private boolean positive(Integer maintenanceOilPrice) {
        return maintenanceOilPrice == null || maintenanceOilPrice == 0;
    }
}


