package victor.training.mindit.sample2;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.asLifoQueue;
import static java.util.Collections.disjoint;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toSet;
import static org.apache.commons.collections.CollectionUtils.isEmpty;
import static org.apache.commons.collections.CollectionUtils.isNotEmpty;

@Slf4j
public class Sample2 {
   private static final Object ENTITY_VF_DETAIL_NAME = 1;

   {

      // imperative shell
      List<VfCommonReqBodyDTO> stuff = new ArrayList<>();
      for (VfCommonReqBodyDTO variableFee : stuff) {
         validateNewAndRetrieveVariableFee(variableFee, getVariableFee(null));
      }
   }

   // you who enter, abdon all bad practices.
   // pure core
    void validateNewAndRetrieveVariableFee(
       VfCommonReqBodyDTO variableFee, VariableFee output
   ) {
      validateDates(variableFee.getStartDate(), variableFee.getEndDate());
//      requireNonNull(variableFee.getStartDate());
//      requireNonNull(variableFee.getEndDate());

      log.debug("check if VF exists in contract details, otherwise, create a new one");

      log.debug("check if overlapping dates are already set in DB");
      List<VariableFeeParam> allParams = output.getVariableFeeDetails().stream()
          .filter(variableFeeDetail -> variableFeeDetail.getDateInterval().overlaps(variableFee.getDateInterval()))
          .flatMap(detail -> detail.getVariableFeeParams().stream())
          .collect(Collectors.toList());

      for (VariableFeeParam param : allParams) validate(param, variableFee);
   }

   private void validate(VariableFeeParam param, VfCommonReqBodyDTO variableFee) {
      Set<Long> brandIds = param.getBrands().stream().map(Brand::getId).collect(toSet());
      Set<String> categoryCodes = param.getCategories().stream().map(Category::getGlobalCode).collect(toSet());

      boolean paramBrands = !brandIds.isEmpty();
      boolean paramCategories = !categoryCodes.isEmpty();
      boolean feeBrands = isNotEmpty(variableFee.getBrandIds());
      boolean feeCategories = !isEmpty(variableFee.getCategoryIds());

      if (!paramBrands && !feeBrands && !paramCategories && !feeCategories) {
         throw new ServiceException(
             "When category and brand are not set, the time period must be unique",
             ENTITY_VF_DETAIL_NAME,
             "datesDuplicate"
         );
      }
      // if category is not present, the time period and brand have to be unique
      if (!brandIds.isEmpty() && feeBrands &&
          !paramCategories && !feeCategories) {
         if (!disjoint(brandIds, variableFee.getBrandIds())) {
            throw new ServiceException(
                "When category is not set, the time period and brand must be unique",
                ENTITY_VF_DETAIL_NAME,
                "datesBrandDuplicate"
            );
         }
      }
      // if brand is not present, the time period and category have to be unique
      if (brandIds.isEmpty() && !feeBrands &&
          paramCategories && feeCategories) {

         if (!disjoint(categoryCodes, variableFee.getCategoryIds())) {
            throw new ServiceException(
                "When brand is not set, the time period and category must be unique",
                ENTITY_VF_DETAIL_NAME,
                "datesCategoryDuplicate"
            );
         }
      }
      // if brand and category are present, the time period, brand and category have to be unique
      if (!brandIds.isEmpty() && feeBrands && paramCategories && feeCategories) {
         if (!disjoint(categoryCodes, variableFee.getCategoryIds())
             && !disjoint(brandIds, variableFee.getBrandIds())) {
            throw new ServiceException(
                "When brand and category are set, the time period, brand and category must be unique",
                ENTITY_VF_DETAIL_NAME,
                "datesBrandCategoryDuplicate"
            );
         }
      }
   }

   private void validateDates(LocalDate startDate, LocalDate endDate) {
//      Stream.of(startDate, endDate).anyMatch(Objects::isNull);
      if (ObjectUtils.anyNull(startDate, endDate)          ) {
         throw new ServiceException(
             "When category and brand are not set, the time period must be unique",
             ENTITY_VF_DETAIL_NAME,
             "datesDuplicate"
         );
      }

   }

   private VariableFee getVariableFee(UUID contractDetailUuid) {
      return null;
   }
}

@Value
class DateInterval {
   LocalDate start;
   LocalDate end;

   //   private <VF extends VfCommonReqBodyDTO> boolean haveOverlappingDates(DateInterval vfdDates, DateInterval dtoDate) {
   public boolean overlaps(DateInterval other) {
      return start.isBefore(other.getEnd()) && getEnd().isAfter(other.getStart());
   }
}