function gmsToggleContractOptions(GarantyIndex) {
	if (GarantyIndex > 0) {

        var Flag1 = jsGarantyKeys[GarantyIndex]['mobilitaet'],
            Flag2 = jsGarantyKeys[GarantyIndex]['kundenbindungsprogramm'],
            Flag3 = jsGarantyKeys[GarantyIndex]['insuredmobility'],
            Flag4 = jsGarantyKeys[GarantyIndex]['letztewartung'],
            Flag5 = jsGarantyKeys[GarantyIndex]['insuredmobility_optional'],
            Box1 = document.getElementById('GarantyOptionKundenbindungsprogramm'),
            Box2 = document.getElementById('GarantyOptionMobilitaet'),
            Box3 = document.getElementById('GarantyOptionInsuredMobility'),
            programType = jsGarantyKeys[GarantyIndex]['producttype'],
            isMaintenanceProduct = (programType == 1),
            isTireProduct = (programType == 2),
            isKMBasedWP = jsGarantyKeys[GarantyIndex]['iskmbasedwp'],
            maintenanceProductContainer = document.getElementById('GarantyOptionMaintenanceProduct'),
            maintenanceProductButton    = document.getElementById('maintenanceProductButton'),
            maintenanceProduct_maxMileage   = $('#maintenanceProduct_maxMileage'),
            tireProductContainer = document.getElementById('GarantyOptionTireProduct'),
            mileageInKilometers = $('#mileageInKilometers2'),
            juristicPerson = jsGarantyKeys[GarantyIndex]['juristicperson'];

        var warrantyprogramId = jsGarantyKeys[GarantyIndex]['id'];
        var moduleOffered = jsGarantyKeys[GarantyIndex]['ismoduleoffered'];
        var isAmountVisible = jsGarantyKeys[GarantyIndex]['isamountvisible'];
        var isImmediateInvoice = jsGarantyKeys[GarantyIndex]['isimmediateinvoice'] === 1;

        RGUtil.broadcastEvent('contract:warrantyProgramChanged', {
            id: warrantyprogramId,
            moduleOffered: moduleOffered,
            isAmountVisible: isAmountVisible,
            isImmediateInvoice: isImmediateInvoice
        });

        $('input[name=warrantyProgramType]').val(programType);

		if (isMaintenanceProduct) {
            //some additional logic at the bottom in document.ready block
            maintenanceProductContainer.style.display = '';
            maintenanceProductButton.style.display = '';
            maintenanceProduct_maxMileage.show();
            mileageInKilometers.show();

		} else {
            maintenanceProductContainer.style.display = '';
            maintenanceProductButton.style.display = 'none';
            maintenanceProduct_maxMileage.val(0);
            maintenanceProduct_maxMileage.hide();
            mileageInKilometers.hide();

            if(isKMBasedWP){
                maintenanceProduct_maxMileage.show();
                mileageInKilometers.show();
            } else {
                maintenanceProduct_maxMileage.hide();
                mileageInKilometers.hide();
              }
		}

        if (isTireProduct) {
            tireProductContainer.style.display = '';

            var uploadParameters = {
                mandnr : window.mainSettings.user.tenantId,
                partnerId : window.mainSettings.user.partnerId,
                hdnr : window.mainSettings.user.companyId,
                type : 9,
                prvid: 0,
                prbankId: 0,
                vtnr : window.mainSettings.URL().get("contract") > 0 ? window.mainSettings.URL().get("contract") : 0
            };

            var uploadUrl = mainSettings.baseUrl + "kontakt/remote/uploadfile.cfm?" + jQuery.param( uploadParameters );

            var uploader = new qq.FileUploader({
                element: this.$('#tireInvoiceUploader')[0],
                action: uploadUrl,
                multiple : false,
                encoding: 'multipart',
                sizeLimit: 31457280,
                allowedExtensions: [],
                template: '<div class="qq-uploader">' +
                            '<div class="qq-upload-drop-area"><span>' + fileUploadDict.dropFilesForUploadTxt + '</span></div>' +
                            '<div class="qq-upload-button">' + fileUploadDict.selectFilesForUploadTxt + '</div>' +
                            '<ul class="qq-upload-list"></ul>' +
                          '</div>',
                fileTemplate: '<li>' +
                                '<span class="qq-upload-file"></span>' +
                                '<span class="qq-upload-spinner"></span>' +
                                '<span class="qq-upload-size"></span>' +
                                '<a class="qq-upload-cancel" href="#">' + fileUploadDict.cancelUploadTxt + '</a>' +
                                '<span class="qq-upload-failed-text">' + fileUploadDict.failedUploadTxt + '</span>' +
                              '</li>',
                messages: {
                    typeError: "{file} hat eine ungültige Endung. Nur {extensions} erlaubt sind.",
                    sizeError: "{file} " + fileUploadDict.sizeErrorTxt + " {sizeLimit}.",
                    minSizeError: "{file} ist zu klein, ist die minimale Dateigröße {minSizeLimit}.",
                    emptyError: "{file} ist leer, bitte wählen Sie Dateien wieder ohne sie.",
                    onLeave: "Die Dateien werden hochgeladen, wenn Sie jetzt den Upload wird abgebrochen verlassen."
                },
                onComplete: function (id, filename, response) {
                    //Deactivate old tire document if exists
                    if ($("#tire_docId").val() != 0) {
                        deleteDocument($("#tire_docId").val(), function(){}, function(){});
                    }

                    $("#tire_docId").val(response.docid);
                    $("#tempDocIds").val($("#tempDocIds").val() == "" ? response.docid : $("#tempDocIds").val() + "," + response.docid);

                    if(response.success) {
                        $(tireProductContainer).find(".qq-upload-file").hide();
                        $(tireProductContainer).find(".qq-upload-size").hide();
                        $(tireProductContainer).find("#tireDocumentLink").html("<a href='" + response.docUrl + "' title='" + response.docClientFile + "'>" + response.docClientFile + "</a>");
                        $(tireProductContainer).find("#deleteTireDocument").show();
                    } else {
                        $(tireProductContainer).find(".qq-upload-button").hide();
                        $(tireProductContainer).find(".qq-upload-list").text(response.error);
                    }
                }
            });

            $(tireProductContainer).find("#deleteTireDocument").click(function() {
                deleteDocument($("#tire_docId").val(), function (infoDialog) {
                    infoDialog.setMessage(kontakt_dict.msgDocumentDelete);
                    infoDialog.hide();
                });

                $("#tire_docId").val(0);

                $(tireProductContainer).find("#tireDocumentLink").html("");
                $(tireProductContainer).find("#deleteTireDocument").hide();
                $(tireProductContainer).find(".qq-upload-button").show();
            });

            if ($(tireProductContainer).find("#tireDocumentLink").html() == "") {
                $(tireProductContainer).find("#deleteTireDocument").hide();
            }

        } else {
            tireProductContainer.style.display = 'none';
        }

		if (Flag1) {
			if (Flag1.value != 0)
				Box1.style.display = '';
		} else {
			Box1.style.display = 'none';
		}

		if (Flag2) {
			if (Flag2.value != 0)
				Box2.style.display = '';
		} else {
			Box2.style.display = 'none';
		}

		if (Flag3) {
			Box3.style.display = '';
			$('#mobilityPremium').text(jsGarantyKeys[GarantyIndex]['formattedpremium']);
			$('#mobilityLabel, #mobilityPremium').removeClass("hidden");

			if (Flag5 == 1){
				$('#insuredMobilityIncludedControl, #mobilityLabel, #mobilityPremium').show();
				$('#insuredMobilityIncludedCheckImage').addClass("hidden");
			} else {
				$('#insuredMobilityIncludedControl').hide();
				$('#insuredMobilityIncludedCheckImage').removeClass("hidden");
				$('#insuredMobilityIncludedControl').prop('checked', true);
				$('#insuredMobilityIncluded').val(1);
			}
		} else {
			Box3.style.display = 'none';
			$('#mobilityLabel, #mobilityPremium').addClass("hidden");
			$('#insuredMobilityIncluded').val(0);
		}

		if (Flag4) {
			if (Flag4.value != 0)
				changecss('.ContractOptionLetzteWartung', 'display', 'block');
			else
				changecss('.ContractOptionLetzteWartung', 'display', 'none');
		}

        if (juristicPerson === 1) {
            $("input[name=kunde_juristicpersontaxid]").addClass("required");
        } else {
            $("input[name=kunde_juristicpersontaxid]").removeClass("required");
        }
	}
}

function updateBrutoRefundAmount(netoField, taxPercentage) {
	var newNeto = parseFloat(normalize(netoField.value)),
		taxAmount = newNeto * parseFloat(taxPercentage) / 100,
		bruto = newNeto + taxAmount;
	if (isNaN(newNeto)) {
		$("button > span:contains('" + gen_dict.yes + "')").parent().prop({ disabled: true });
		$("#premium_refund_amount_neto").addClass("redBorder");
		$("#flash").html(validation_dict.invalidFloat).addClass("red").show();
	} else {
		$("#flash").html("").removeClass("red").hide();
		$("#premium_refund_amount_neto").removeClass("redBorder");
		$("#premium_refund_amount_bruto").val(localize(parseFloat(bruto).toFixed(2)));
		$("button > span:contains('" + gen_dict.yes + "')").parent().prop({ disabled: false });
	}
}

$(document).ready(function() {
	$('#insuredMobilityIncludedControl').on('click', function(e) {
		var newValue = $(this).prop('checked') ? 1 : 0;
		$('#insuredMobilityIncluded').val(newValue);
	});

	$('#GarantyIndex').on('change', function(e) {
		gmsToggleContractOptions($(this).val());
	});
});
