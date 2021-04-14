package victor.training.sample.maintanance;

public class GetMaintenancePlanRequestWarranted {
   private final GetMaintenancePlanRequest maintenancePlanRequest;
   private final WarrantyProgram warrantyProgram;

   public GetMaintenancePlanRequestWarranted(GetMaintenancePlanRequest maintenancePlanRequest, WarrantyProgram warrantyProgram) {

      this.maintenancePlanRequest = maintenancePlanRequest;
      this.warrantyProgram = warrantyProgram;
   }

   public Integer getOilPrice() {
      return warrantyProgram.getAmendment().getMaintenanceOilPrice();
   }
   public Integer getMaterialPartPercent() {
      return warrantyProgram.getAmendment().getMaterialPartPercent();
   }
   //        warranted.setOilPrice(warrantyProgram.getAmendment().getMaintenanceOilPrice());
//        warranted.setWagePrice(warrantyProgram.getAmendment().getMaintenanceWagePrice());
//        warranted.setMaterialPartPercent(warrantyProgram.getAmendment().getMaterialPartPercent());
//        warranted.setWorkHour(warrantyProgram.getAmendment().getWorkHour());

}
