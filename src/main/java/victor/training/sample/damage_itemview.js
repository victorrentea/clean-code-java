// _.prototype.newFu=function() {
//
// }
alternativeCostProperties = {
    canBeNull: false,
    minLength: 4
}

var item = {
    changed: true
}

function validateAndDisplay(val, originalCost) {
    var originalCostField = new DataValidation($(this).val(), originalCostProperties);
    return originalCostField.validate();
}

var DamageItemFieldset = function (damageItem, thisPageProperties) {

    function onChangeWage($element, self) {
        var wageCostField = new DataValidation($element.val(), wageCostProperties);
        var oldReserve = item.get("reserve");
        if (wageCostField.validate()) {
            $element.removeClass("invalidItemField");
            item.set({wageCost: normalize($element.val())});
            $element.val(localize($element.val()));
            self.updateSumField("totalCost", item.getTotalCost());
            self.updateSumField("wageWarranty", item.getWageWarranty());
            self.updateSumField("wageCustomer", item.getWageCustomer());
            self.updateSumField("totalCustomer", item.getTotalCustomer());
            self.updateSumField("reserve", item.getReserve());
            self.updateSubTotalReserveField(oldReserve);
            thisPageProperties.validationMessages.remove(item, wageCostProperties.name);
        } else {
            $element.addClass("invalidItemField");
            thisPageProperties.validationMessages.add(item, wageCostProperties);
        }
    }

    addChangeListeners : function () {
        let self = this;
        let id = item.get("id");

        item.on("change", function () { // item.changed = {"matchingDmgDescription_id": ....}
            if (_.has(item.changed, "matchingDmgDescription_id")) {
                item.get("matchingDmgDescription").set({changed: true});
            } else {
                item.set({changed: true});
            }

        });
        $("#sonper_" + id).bind("change", function () {
            if ($("#sonper_" + id).val() === "") {
                item.set({alternativeRepairerId: "0"});
            }
        }),
            $("#wageCost_" + id).bind("change", function () {
                onChangeWage($(this), self);
            });

        $("#originalPartPrice_" + id).on("change", function () {
            // var originalCostField = new DataValidation($(this).val(), originalCostProperties);
            // if (originalCostField.validate()) {
            // var originalCostField = new DataValidation($(this).val(), originalCostProperties);
            if (validateAndDisplay($(this).val(), Constraints.originalCost)) {
                item.set({originalPartPrice: normalize($(this).val())});
                self.updateSavedMoneyField();

                $(this).removeClass("invalidItemField");
                $(this).val(localize($(this).val()));
                thisPageProperties.validationMessages.remove(item, originalCostProperties.name);
            } else {
                $(this).addClass("invalidItemField");
                thisPageProperties.validationMessages.add(item, originalCostProperties);
            }
        });
        $("#alternativeRepairerId_" + id).on("change", function () {
            var alternativeRepairerId = $(this).val();
            item.set({alternativeRepairerId: $(this).val()});
        });
        $("#alternativePartPrice_" + id).on("change", function () {
            var alternativeCostField = new DataValidation($(this).val(), alternativeCostProperties);
            if (alternativeCostField.validate()) {
                $(this).removeClass("invalidItemField");
                item.set({alternativePartPrice: normalize($(this).val())});
                $(this).val(localize($(this).val()));
                self.updateSavedMoneyField();
                thisPageProperties.validationMessages.remove(item, alternativeCostProperties.name);
            } else {
                $(this).addClass("invalidItemField");
                thisPageProperties.validationMessages.add(item, alternativeCostProperties);
            }
        });
        $("#promissedExtraCharge_" + id).on("change", function () {
            var promissedExtraChargeField = new DataValidation($(this).val(), promissedExtraChargeProperties);
            if (promissedExtraChargeField.validate()) {
                $(this).removeClass("invalidItemField");
                item.set({promissedExtraCharge: normalize($(this).val())});
                $(this).val(localize($(this).val()));
                self.updateSavedMoneyField();
                thisPageProperties.validationMessages.remove(item, promissedExtraChargeProperties.name);
            } else {
                $(this).addClass("invalidItemField");
                thisPageProperties.validationMessages.add(item, promissedExtraChargeProperties);
            }
        });
        $("#materialCost_" + id).bind("change", function () {
            var materialCostField = new DataValidation($(this).val(), materialCostProperties),
                oldReserve = item.get("reserve");
            if (materialCostField.validate()) {
                $(this).removeClass("invalidItemField");
                item.set({materialCost: normalize($(this).val())});
                $(this).val(localize($(this).val()));
                self.updateSumField("totalCost", item.getTotalCost());
                self.updateSumField("materialWarranty", item.getMaterialWarranty());
                self.updateSumField("materialCustomer", item.getMaterialCustomer());
                self.updateSumField("totalCustomer", item.getTotalCustomer());
                self.updateSumField("reserve", item.getReserve());
                self.updateSubTotalReserveField(oldReserve);
                thisPageProperties.validationMessages.remove(item, materialCostProperties.name);
            } else {
                $(this).addClass("invalidItemField");
                thisPageProperties.validationMessages.add(item, materialCostProperties);
            }
        });
        $("#otherCost_" + id).bind("change", function () {
            var otherCostField = new DataValidation($(this).val(), otherCostProperties),
                oldReserve = item.get("reserve");
            if (otherCostField.validate()) {
                $(this).removeClass("invalidItemField");
                item.set({otherCost: normalize($(this).val())});
                $(this).val(localize($(this).val()));
                self.updateSumField("totalCost", item.getTotalCost());
                self.updateSumField("reserve", item.getReserve());
                self.updateSubTotalReserveField(oldReserve);
            } else {
                $(this).addClass("invalidItemField");
            }
        });
        $("#tireDepth_" + id).bind("change", function () {
            var tireDepthField = new DataValidation($(this).val(), tireDepthProperties),
                oldReserve = item.get("reserve");
            if (tireDepthField.validate()) {
                $(this).removeClass("invalidItemField");
                item.set({tireDepth: cutOffDecimalPlaces(normalize($(this).val()), 1)});
                $(this).val(localize(item.get("tireDepth")));
                self.updateSumField("totalCost", item.getTotalCost());
                self.updateSumField("wageWarranty", item.getWageWarranty());
                self.updateSumField("wageCustomer", item.getWageCustomer());
                self.updateSumField("materialWarranty", item.getMaterialWarranty());
                self.updateSumField("materialCustomer", item.getMaterialCustomer());
                self.updateSumField("totalCustomer", item.getTotalCustomer());
                self.updateSumField("reserve", item.getReserve());
                self.updateSubTotalReserveField(oldReserve);
                var tireDepthPercentage = item.findCorrectPercentageInTireDepthRules(item.get("tireDepth"));
                self.updateLabelField("label_materialWarranty", damage_dict.materialWarranty + " (" + tireDepthPercentage + "%)");
                thisPageProperties.validationMessages.remove(item, tireDepthProperties.name);
            } else {
                $(this).addClass("invalidItemField");
                thisPageProperties.validationMessages.add(item, tireDepthProperties);
            }
        });
        $("#tireDepth_" + id).ready(function () {
            $("#tireDepth_" + id).trigger("change");
        });
        $("#wageGrace_" + id).bind("change", function () {
            var wageGraceField = new DataValidation($(this).val(), wageGraceProperties),
                oldReserve = item.get("reserve");
            if (wageGraceField.validate()) {
                $(this).removeClass("invalidItemField");
                item.set({wageGracePercentage: normalize($(this).val())});
                $(this).val(localize($(this).val()));
                self.updateSumField("totalGrace", item.getTotalGrace());
                self.updateSumField("wageWarranty", item.getWageWarranty());
                self.updateSumField("wageCustomer", item.getWageCustomer());
                self.updateSumField("totalCustomer", item.getTotalCustomer());
                self.updateSumField("reserve", item.getReserve());
                self.updateSubTotalReserveField(oldReserve);
            } else {
                $(this).addClass("invalidItemField");
            }
        });
        $("#materialGrace_" + id).bind("change", function () {
            var materialGraceField = new DataValidation($(this).val(), materialGraceProperties),
                oldReserve = item.get("reserve");
            if (materialGraceField.validate()) {
                $(this).removeClass("invalidItemField");
                item.set({materialGracePercentage: normalize($(this).val())});
                $(this).val(localize($(this).val()));
                self.updateSumField("totalGrace", item.getTotalGrace());
                self.updateSumField("materialWarranty", item.getMaterialWarranty());
                self.updateSumField("materialCustomer", item.getMaterialCustomer());
                self.updateSumField("totalCustomer", item.getTotalCustomer());
                self.updateSumField("reserve", item.getReserve());
                self.updateSubTotalReserveField(oldReserve);
            } else {
                $(this).addClass("invalidItemField");
            }
        });
        $("#otherGrace_" + id).bind("change", function () {
            var otherGraceField = new DataValidation($(this).val(), otherGraceProperties),
                oldReserve = item.get("reserve");
            if (otherGraceField.validate()) {
                $(this).removeClass("invalidItemField");
                item.set({otherGracePercentage: normalize($(this).val())});
                $(this).val(localize($(this).val()));
                self.updateSumField("totalGrace", item.getTotalGrace());
                self.updateSumField("reserve", item.getReserve());
                self.updateSubTotalReserveField(oldReserve);
            } else {
                $(this).addClass("invalidItemField");
            }
        });
        $("#remarks_" + id).bind("change", function () {
            item.set({remarks: $(this).val()});
        });
        if (!thisPageProperties.insurance) {
            $("#otherCustomer_" + id).bind("change", function () {
                var otherCustomerField = new DataValidation($(this).val(), otherCustomerProperties);
                if (otherCustomerField.validate()) {
                    $(this).removeClass("invalidItemField");
                    item.set({otherCustomer: normalize($(this).val())});
                    $(this).val(localize($(this).val()));
                    self.updateSumField("totalCustomer", item.getTotalCustomer());
                } else {
                    $(this).addClass("invalidItemField");
                }
            });
        }
    }
,

    addClickListeners : function () {
        var self = this;
        var id = item.get("id");
        $("h3#" + id).bind("click", function () {
            toggleActiveItem(item.get("id"), item.get("damageTypeId"));
        });

        $("img#delete_" + id).bind("click", function () {
            self.remove();b
        });


        // function disableElements(cs)
        function disableScreen() {
            item.set({overhauling: 0});
            $("#originalPartPrice_" + id).prop("disabled", true);
            $("#alternativePartPrice_" + id).prop("disabled", true);
            $("#promissedExtraCharge_" + id).prop("disabled", true);
            $("#sonper_" + id).prop("disabled", true);
            $("#alternativeRepairerId_" + id).prop("disabled", true);

            $("#originalPartPrice_" + id).val("0");
            $("#alternativePartPrice_" + id).val("0");
            $("#promissedExtraCharge_" + id).val("0");
            $("#savedMoney_" + id).val("0");
            $("#sonper_" + id).val("");
            $("#alternativeRepairerId_" + id).val("0");

            item.set("alternativePartPrice", 0);
            item.set("originalPartPrice", 0);
            item.set("promissedExtraCharge", 0);
            item.set("alternativeRepairerId", 0);
        }

        $("#overhauling_" + id).on("click", function () {
            if ($("#overhauling_" + id)[0].checked === true) {
                item.set({overhauling: 1});

                var selectCallback = function (event, ui) {
                    var id = event.target.id.split("_")[1];
                    $(this).val(ui.item.name);

                    if (ui.item) {
                        var serviceProviderData = ui.item;
                        $("input[id='alternativeRepairerId_" + id + "']")
                            .val(serviceProviderData.serviceProviderId)
                            .trigger('change');
                    }
                    return false;
                };
                loadServiceProviders("#sonper_" + id, thisPage.damage.hdnr, thisPage.damage.filial, false, selectCallback, 7);
                $("#originalPartPrice_" + id).prop("disabled", false);
                $("#alternativePartPrice_" + id).prop("disabled", false);
                $("#promissedExtraCharge_" + id).prop("disabled", false);
                $("#sonper_" + id).prop("disabled", false);
                $("#alternativeRepairerId_" + id).prop("disabled", false);
            } else {
                disableScreen();
            }
        });
    }
,
		