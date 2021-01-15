package victor.training.mindit.sample2;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

import static java.util.Collections.disjoint;

@Slf4j
public class Sample2 {
   private static final Object ENTITY_VF_DETAIL_NAME = 1;

   protected <VF extends VfCommonReqBodyDTO> VariableFee validateNewAndRetrieveVariableFee(
       final VF variableFeeDTO,
       final UUID contractDetailUuid
   ) {
      log.debug("check if the startDate and endDate are present");
      validateDates(variableFeeDTO.getStartDate(), variableFeeDTO.getEndDate());

      log.debug("check if VF exists in contract details, otherwise, create a new one");
      VariableFee output = getVariableFee(contractDetailUuid);
      BiPredicate<VariableFeeDetail, VF> haveOverlappingDates = (vfd, dto) ->
          vfd.getStartDate().isBefore(dto.getEndDate())
          && vfd.getEndDate().isAfter(dto.getStartDate());

      log.debug("check if overlapping dates are already set in DB");
      output.getVariableFeeDetails().stream()
          // check for each variableFeeDetail that is being created
          // and has overlapping dates in the database
          .filter(variableFeeDetail -> haveOverlappingDates.test(variableFeeDetail, variableFeeDTO))
          .forEach(vfd -> vfd.getVariableFeeParams().forEach(vfp -> {
             // if category and brand are not present, the time period has to be unique
             if (vfp.getBrands().isEmpty()
                 && vfp.getCategories().isEmpty()
                 && (variableFeeDTO.getBrandIds() == null
                     || variableFeeDTO.getBrandIds() != null && variableFeeDTO.getBrandIds().isEmpty())
                 && (variableFeeDTO.getCategoryIds() == null
                     || variableFeeDTO.getCategoryIds() != null && variableFeeDTO.getCategoryIds().isEmpty())
             ) {
                throw new ServiceException(
                    "When category and brand are not set, the time period must be unique",
                    ENTITY_VF_DETAIL_NAME,
                    "datesDuplicate"
                );
             }
             // if category is not present, the time period and brand have to be unique
             if (!vfp.getBrands().isEmpty()
                 && vfp.getCategories().isEmpty()
                 && variableFeeDTO.getBrandIds() != null && !variableFeeDTO.getBrandIds().isEmpty()
                 && (variableFeeDTO.getCategoryIds() == null
                     || variableFeeDTO.getCategoryIds() != null && variableFeeDTO.getCategoryIds().isEmpty())
             ) {
                Set<Long> brandIds = vfp.getBrands().stream().map(Brand::getId)
                    .collect(Collectors.toSet());
                // disjoint returns true if the two specified collections have no elements in common.
                if (!disjoint(brandIds, variableFeeDTO.getBrandIds())) {
                   throw new ServiceException(
                       "When category is not set, the time period and brand must be unique",
                       ENTITY_VF_DETAIL_NAME,
                       "datesBrandDuplicate"
                   );
                }
             }
             // if brand is not present, the time period and category have to be unique
             if (vfp.getBrands().isEmpty()
                 && !vfp.getCategories().isEmpty()
                 && (variableFeeDTO.getBrandIds() == null
                     || variableFeeDTO.getBrandIds() != null && variableFeeDTO.getBrandIds().isEmpty())
                 && variableFeeDTO.getCategoryIds() != null && !variableFeeDTO.getCategoryIds().isEmpty()
             ) {
                Set<String> categoryGlobalCodes = vfp.getCategories().stream()
                    .map(Category::getGlobalCode)
                    .collect(Collectors.toSet());
                // disjoint returns true if the two specified collections have no elements in common.
                if (!disjoint(categoryGlobalCodes, variableFeeDTO.getCategoryIds())) {
                   throw new ServiceException(
                       "When brand is not set, the time period and category must be unique",
                       ENTITY_VF_DETAIL_NAME,
                       "datesCategoryDuplicate"
                   );
                }
             }
             // if brand and category are present, the time period, brand and category have to be unique
             if (!vfp.getBrands().isEmpty()
                 && !vfp.getCategories().isEmpty()
                 && variableFeeDTO.getBrandIds() != null && !variableFeeDTO.getBrandIds().isEmpty()
                 && variableFeeDTO.getCategoryIds() != null && !variableFeeDTO.getCategoryIds().isEmpty()
             ) {
                Set<String> categoryGlobalCodes = vfp.getCategories().stream()
                    .map(Category::getGlobalCode)
                    .collect(Collectors.toSet());
                Set<Long> brandIds = vfp.getBrands().stream().map(Brand::getId)
                    .collect(Collectors.toSet());
                // disjoint returns true if the two specified collections have no elements in common.
                if (!disjoint(categoryGlobalCodes, variableFeeDTO.getCategoryIds())
                    && !disjoint(brandIds, variableFeeDTO.getBrandIds())) {
                   throw new ServiceException(
                       "When brand and category are set, the time period, brand and category must be unique",
                       ENTITY_VF_DETAIL_NAME,
                       "datesBrandCategoryDuplicate"
                   );
                }
             }

          }));

      return output;
   }

   private void validateDates(LocalDate startDate, LocalDate endDate) {

   }

   private VariableFee getVariableFee(UUID contractDetailUuid) {
      return null;
   }
}
