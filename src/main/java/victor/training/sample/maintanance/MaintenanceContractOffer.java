package victor.training.sample.maintanance;

import javax.persistence.Transient;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static java.math.BigDecimal.valueOf;
import static java.math.RoundingMode.HALF_UP;

public class MaintenanceContractOffer {
   public MaintenanceContractOffer(GetMaintenancePlanRequest maintenancePlanRequest, SaveMaintenancePlanResponse maintenancePlanResponse, WarrantyProgram warrantyProgram, Partner partner) {
      this.setTenantId(partner.getTenantId());
      this.setCompanyNumber(partner.getCompanyNumber());
      this.setDealerNumber(partner.getDealerNumber());
      this.setMakeGroup(warrantyProgram.getMakeGroup());
      this.setKind(warrantyProgram.getKind());
      this.setProduct(warrantyProgram.getProduct());
      this.setProductName(warrantyProgram.getProductName() + " - " + warrantyProgram.getKind() + " (" + warrantyProgram.getProduct() + ")");
      this.setValidFrom(warrantyProgram.getValidFrom());
      this.setPeriod(maintenancePlanRequest.getMonthTo() - maintenancePlanRequest.getMonthFrom());
      this.setMileage(maintenancePlanRequest.getMileageTo() - maintenancePlanRequest.getMileageFrom());
      this.setOilQuantity(maintenancePlanResponse.getOilQuantity());
      this.setWorkHours(maintenancePlanResponse.getWorkHours());
      this.setPartsCost(maintenancePlanResponse.getPartsCost());
      this.setDocId(maintenancePlanResponse.getDocId());
      this.setModules(maintenancePlanRequest.getModules());
      this.setModulesName(maintenancePlanRequest.getModulesName().split(";"));
      this.setFirstRegistration(maintenancePlanRequest.getFirstRegistration());
      this.setMileageFrom(maintenancePlanRequest.getMileageFrom());
      this.setVehicleCode(maintenancePlanRequest.getVehicleCode());
      this.setWarrantyProgramId(maintenancePlanRequest.getWarrantyProgramId());
   }


   public Long getDocId() {
      return null;
   }

   public void setNumberOfMaintenance(Long numberOfMaintenance) {

   }

   BigDecimal grossPrice;
   int period;
   public BigDecimal getGrossPrice() {
      return grossPrice;
   }

   public long getPeriod() {
      return period;
   }

   @Transient
   BigDecimal monthlyCost;
//   public void setMonthlyCost(BigDecimal divide) {
//
//
//   }

   @Transient
   public BigDecimal getMonthlyCost() {
      return grossPrice.divide(valueOf(period), 2, HALF_UP);
   }

   public void setTenantId(String tenantId) {
      
   }

   public void setCompanyNumber(String companyNumber) {
   }

   public void setDealerNumber(String dealerNumber) {
   }

   public void setMakeGroup(String makeGroup) {
   }

   public void setKind(Kind kind) {
   }

   public void setProductName(String s) {
   }

   public void setProduct(String product) {
   }

   public void setValidFrom(LocalDate validFrom) {
   }

   public void setPeriod(int months) {
      
   }

   public void setMileage(int i) {
   }

   public void setOilQuantity(int oilQuantity) {
   }

   public void setWorkHours(String workHours) {
   }

   public void setPartsCost(int partsCost) {
   }

   public void setDocId(Long docId) {
   }

   public void setModulesName(String[] split) {
   }

   public void setModules(List<String> modules) {
   }

   public void setFirstRegistration(LocalDate firstRegistration) {
   }

   public void setMileageFrom(int mileageFrom) {
   }

   public void setVehicleCode(String vehicleCode) {
   }

   public void setWarrantyProgramId(Long warrantyProgramId) {
   }
}
