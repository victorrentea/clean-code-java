component accessors="true" output="false"{
	property name="contract";
	property name="warrantyProgram";
	property name="printLocale" type="string" default="de_DE";
	property name="company";
	property name="dealer";

//...

	private struct function warrantyData(){
		//globalToComponent = 1;
		var aLabel = ArrayNew(1);
		var aData = ArrayNew(1);
		var warrantyData = {};
		var stGSC = {};
		var iField = 1;
		var contract = getContract();
		var warrantyProgram = getWarrantyProgram();
		var locale = getPrintLocale();
		var maxMileage = getWarrantyMileageLimit();

		if(templateDocument == "gav_bv.jasper") {
			arrayAppend(aLabel, translation.getText("rgtext", "descriptionBW", locale));
			arrayAppend(aData, warrantyProgram.Beschreibung);
		}
		ArrayAppend(aLabel, translation.getText("rgtext", "garantienr", locale));
		ArrayAppend(aData, Trim(warrantyProgram.IDPrefix & " " & contract.ID));
		if(!isWarrantyProgramNetherlandsInsuriance()){
			ArrayAppend(aLabel, translation.getText("rgtext", "maintenanceCode", locale));
			ArrayAppend(aData, contract.GSCCode);
		}
		ArrayAppend(aLabel, translation.getText("rgtext", "laufzeit", locale) & " ");
		ArrayAppend(aData, contract.Garantie.Laufzeit & " " & translation.getText("rgtext", "monate", locale));

		// redudant assignment for gav_a_wp.jasper
		if(templateDocument == "gav_bv.jasper") {
			warrantyData["description"] = translation.getText("rgtext", "description", locale);
			warrantyData["descriptionValue"] = warrantyProgram.Beschreibung;
		}
		warrantyData["garantienr"] = translation.getText("rgtext", "garantienr", locale);
		warrantyData["garantienrValue"] = Trim(warrantyProgram.IDPrefix & " " & contract.ID);
		warrantyData["garantiecode"] = translation.getText("rgtext", "garantiecode", locale);
		warrantyData["garantiecodeValue"] = contract.GSCCode;
		warrantyData["totalBrutto"] = numberUtilities.localizeAsCurrency(contract.rueckstellungTotalBrutto, locale, warrantyProgram.Currency);
		warrantyData["laufzeit"] = translation.getText("rgtext", "laufzeit", locale) & " ";
		warrantyData["laufzeitValue"] = contract.Garantie.Laufzeit & " " & translation.getText("rgtext", "monate", locale);
		// end of redudant assignment

		var results = getWPMilageInformation(warrantyData, aLabel, aData);
		aLabel = results.aLabel;
		aData = results.aData;

		if(warrantyProgram.regulierung.grenzwerte[laufzeitindex].laufleistungVerkauf) {
			warrantyData["maxValidPeriodSincePurchaseLabel"] = remapts(translation.getText("rgtext", "maxValidityPeriodSincePurchase", locale), locale);
			warrantyData["maxValidPeriodSincePurchaseValue"] = numberUtilities.localize(warrantyProgram.Regulierung.Grenzwerte[LaufzeitIndex].LaufleistungVerkauf, locale, 0) & " km";
		}
		warrantyData["maxMileageStricker"] = numberUtilities.localize(warrantyProgram.Regulierung.Grenzwerte[LaufzeitIndex].LaufleistungVerkauf + contract.Fahrzeug.Kilometerstand, locale, 0) & " km";
		ArrayAppend(aLabel, translation.getText("rgtext", "beginn", locale));
		ArrayAppend(aData, dateUtilities.localize(contract.LaufzeitbeginnExtern, tenantdao.getTenantLocale(contract.tenant)));
		ArrayAppend(aLabel, translation.getText("rgtext", "ende", locale));
		ArrayAppend(aData, dateUtilities.localize(contract.Laufzeitende, tenantdao.getTenantLocale(contract.tenant)));
		// start and end date of warranty for AV mandant
		warrantyData["warrantyStartLabel"] = remapts(translation.getText("rgtext", "beginn", locale), locale);
		warrantyData["warrantyStartLabelUK"] = translation.getText("rgtext", "beginnUK", locale);
		warrantyData["warrantyStartValue"] = dateUtilities.localize(contract.LaufzeitbeginnExtern, tenantdao.getTenantLocale(contract.tenant));
		warrantyData["warrantyEndLabel"] = remapts(translation.getText("rgtext", "ende", locale), locale);
		warrantyData["warrantyEndLabelUK"] = translation.getText("rgtext", "endeUK", locale);
		warrantyData["warrantyEndValue"] =  dateUtilities.localize(contract.Laufzeitende, tenantdao.getTenantLocale(contract.tenant));
        warrantyData["selbstbehalt"] = contract.Selbstbehalt2;
		if(contract.Selbstbehalt or contract.Selbstbehalt2 or contract.SelbstbehaltFremdwerkstatt) {
			ArrayAppend(aLabel, translation.getText("rgtext", "selbst", locale));
			// redudant assignment for gav_a_wp.jasper
			warrantyData["selbst"] = translation.getText("rgtext", "selbst", locale);
			// end of redudant assignment
			if((contract.Selbstbehalt or contract.Selbstbehalt2)
				and not contract.SelbstbehaltFremdwerkstatt
				and contract.Selbstbehalt is contract.Selbstbehalt2) {
					ArrayAppend(aData, application.globals.gmsPDFEscape(application.globals.gmsGetCurrencyFormat(Amount = contract.Selbstbehalt, Currency = warrantyProgram.Currency, IsHTMLEscaped = FALSE)));
					// redudant assignment for gav_a_wp.jasper
					warrantyData["selbstValue"] = application.globals.gmsPDFEscape(application.globals.gmsGetCurrencyFormat(Amount = contract.Selbstbehalt, Currency = warrantyProgram.Currency, IsHTMLEscaped = FALSE));
					// end of redudant assignment
			} else {
				ArrayAppend(aData, translation.getText("rgtext", "sonstige", locale));
				// redudant assignment for gav_a_wp.jasper
				warrantyData["selbstValue"] = translation.getText("rgtext", "sonstige", locale);
				// end of redudant assignment
			}
		}


		if(contract.Regulierungsbetrag) {
			ArrayAppend(aLabel, translation.getText("rgtext", "maxreg", locale));
			ArrayAppend(aData, application.globals.gmsGetCurrencyFormat(Amount = contract.Regulierungsbetrag, Currency = warrantyProgram.Currency, IsHTMLEscaped = FALSE));
			// redudant assignment for gav_a_wp.jasper
			warrantyData["maxreg"] = translation.getText("rgtext", "maxreg", locale);
			warrantyData["maxregValue"] = application.globals.gmsGetCurrencyFormat(Amount = contract.Regulierungsbetrag, Currency = warrantyProgram.Currency, IsHTMLEscaped = FALSE);
			// end of redudant assignment
			warrantyData["maximumCompensationPerWarrantyLabel"] = translation.getText("rgtext", "maximumCompensationPerWarranty", locale);
			warrantyData["maximumCompensationPerWarrantyValue"] = numberUtilities.localizeAsCurrency(contract.Regulierungsbetrag, locale, warrantyProgram.Currency);
		}
		warrantyData["maxMileageLabel"] = translation.getText("rgtext", "mileageLimit", locale);
		for(iField = 1; iField <= Min(8, ArrayLen(aData)); iField++) {
			warrantyData["GL" & iField] = aLabel[iField];
			warrantyData["GD" & iField] = aData[iField];
		}
		warrantyData["warrantyNumberBW"] = translation.getText("rgtext", "warrantyNumberBW", locale);
		warrantyData["warrantyCodeBW"] = translation.getText("rgtext", "warrantyCodeBW", locale);
		// Guarantee labels for AV mandant
		warrantyData["guaranteePeriodLabel"] = translation.getText("rgtext", "laufzeit", locale);
		warrantyData["guaranteePeriodLabelUK"] = translation.getText("rgtext", "laufzeitUK", locale);

		if(warrantyProgram.Regulierung.Grenzwerte[LaufzeitIndex].Regulierungsbetrag) {
			warrantyData["highestRateLabel"] = remapts(translation.getText("rgtext", "highestRate", locale), locale);
			warrantyData["highestRateLabelUK"] = translation.getText("rgtext", "highestRateUK", locale);
			warrantyData["highestRateValue"] = application.globals.gmsGetCurrencyFormat(Amount = contract["regulierungsbetrag"], Currency = warrantyProgram.Currency, IsHTMLEscaped = FALSE);
        }

		if(contract.DatumAlte != 0){
			warrantyData["secondHighestRateValue"] = application.globals.gmsGetCurrencyFormat(Amount = contract.regulierungsbetragAlte, Currency = warrantyProgram.Currency, IsHTMLEscaped = FALSE);
			warrantyData["secondHighestRateLabel"] = remapts(translation.getLabel("vs_from")& " " & application.globals.gmsDateFormat(contract.DatumAlte), locale);
		}

		if(warrantyProgram.Regulierung.Grenzwerte[LaufzeitIndex].Selbstbeteiligung) {
			if(warrantyProgram.getCompany().getTenant().isRomanian())
				warrantyData["deductibleLabel"] = remapts(translation.getText("rgtext", "deductible1", locale), locale);
			else
				warrantyData["deductibleLabel"] = remapts(translation.getText("rgtext", "deductible", locale), locale);
			warrantyData["deductibleValue"] = application.globals.gmsGetCurrencyFormat(Amount = warrantyProgram.Regulierung.Grenzwerte[LaufzeitIndex].Selbstbeteiligung, Currency = warrantyProgram.Currency, IsHTMLEscaped = FALSE);
		}


		if (len(trim(warrantyProgram.DocumentTemplates.GAB)) > 3){
			warrantyData["disclaimerLabel"] = translation.getText("rgtext", "applicableWarranty", locale);
            warrantyData["disclaimerLabelKollektiv"] = translation.getText("rgtext", "disclaimerLabelKollektiv", locale);
			warrantyData["disclaimerLabelVW"] = translation.getText("rgtext", "disclaimerLabelVW", locale);
			warrantyData["disclaimerValue"] = left(trim(warrantyProgram.DocumentTemplates.GAB),len(trim(warrantyProgram.DocumentTemplates.GAB)) - 4);
		}
		//(Wartung)
		if(isSchubertMotorsBMW()) {
			warrantyData["bruto"] = translation.getText("rgtext", "bruto", locale);
			warrantyData["maxMaintainanceSum"] = translation.getText("rgtext", "maxMaintainanceSum", locale);
			warrantyData["bruttoPremiumValue"] = application.globals.gmsGetCurrencyFormat(Amount = contract.rueckstellungTotalBrutto, Currency = warrantyProgram.Currency, IsHTMLEscaped = FALSE);
		}

		if(isWarrantyProgramSwissCHV() && (contract.Regulierungsbetrag != 0.00)){
		warrantyData["maxreg"] = translation.getText("rgtext", "maxreg", locale);
        warrantyData["maxregValue"] = LSNumberFormat(contract.Regulierungsbetrag,"9,999,999.99",locale);
		}

		warrantyData["bruttoPremiumLabel"] = translation.getText("rgtext", "bruttoPremiumLabel", locale);
		if(contract.getManualPremiumOffer() neq ""){
			warrantyData["bruttoPremiumValue"] = application.globals.gmsGetCurrencyFormat(Amount = contract.getManualPremiumOffer(), Currency = warrantyProgram.Currency, IsHTMLEscaped = FALSE);
		}else{
			warrantyData["bruttoPremiumValue"] = application.globals.gmsGetCurrencyFormat(Amount = contract.rueckstellungTotalBrutto, Currency = warrantyProgram.Currency, IsHTMLEscaped = FALSE);
		}

        warrantyData["netoPremiumValue"] = application.globals.gmsGetCurrencyFormat(Amount = contract.rueckstellung, Currency = warrantyProgram.Currency, IsHTMLEscaped = FALSE);
        warrantyData["premiumTaxValue"] = application.globals.gmsGetCurrencyFormat(Amount = contract.rueckstellungTotalBrutto-contract.rueckstellung, Currency = warrantyProgram.Currency, IsHTMLEscaped = FALSE);
		warrantyData["GNRL"] = translation.getText("rgtext", "garantienr", locale);
		warrantyData["GNRD"] = Trim(warrantyProgram.IDPrefix & " " & contract.ID);
		warrantyData["GBL"] = translation.getText("rgtext", "beginn", locale);
		warrantyData["GBD"] = dateUtilities.localize(contract.LaufzeitbeginnExtern);
		warrantyData["GEL"] = translation.getText("rgtext", "ende", locale);
		warrantyData["GED"] = dateUtilities.localize(contract.Laufzeitende);
		warrantyData["LZL"] = translation.getText("rgtext", "laufzeit", locale);
		warrantyData["LZD"] = contract.Garantie.Laufzeit & " " & translation.getText("rgtext", "monate", locale);
        warrantyData["LZD"] = contract.Garantie.Laufzeit & " " & translation.getText("rgtext", "monate", locale);
		warrantyData["maintenanceCode"] = translation.getText("rgtext", "maintenanceCode", locale);
        warrantyData["isMonthlyPayment"] = monthlyPaymentType().isMonthlyPayment;
		warrantyData["einmalzahlungMark"] = "X";
            if(warrantyData["isMonthlyPayment"]){
			warrantyData["monthlyPaymentMark"] = "X";
			warrantyData["einmalzahlungMark"] = "";
			warrantyData["firstRateAmount"] = numberUtilities.localizeAsCurrency(number = (NumberFormat(contract.rueckstellungTotalBrutto/contract.Garantie.Laufzeit, "9.99")), currencySymbol = company.Currency);
		}

		warrantyData["paymentType"] = resolvePaymentType(monthlyPaymentType().paymentTypeId);

		warrantyData["isKMBasedWP"] = warrantyProgram.isKMBasedWP() == "YES" ? true : false ;
		warrantyData["mileageInKilometers"] = translation.getText("rgtext", "mileageInKilometers", locale);
		warrantyData["mileageInKilometersValue"] = numberUtilities.localize(contract.maintenanceProduct.maxMileage, locale, 0) & " km";
		warrantyData["isModulePresent"] = contract.isModulePresent;

		return warrantyData;
	}


	private String function resolvePaymentType(numeric paymentTypeId) {
		switch (monthlyPaymentType().paymentTypeId){
			case 1:
				return "Einmalzahlung";
			case 2:
				return "Monatliche Zahlweise";
			case 3:
				return "Quartal Zahlweise";
			default:
				return "Einmalzahlung";
		}
	}


	private string function otherConditionsData(){

		// callSomeNewJavaFunctionality();

		var conditions = "";
		var contract = getContract();
		var warrantyProgram = getWarrantyProgram();
		var locale = getPrintLocale();
		if(isSlovenia(warrantyProgram.tenant) {//6){
			conditions = translation.getText("rgtext", "sbuvForeText", locale);
		}
		if(!warrantyProgram.isCEETemplate(templateDocument) ){
			if(isForeignRepairShop) {
				// someFunctionGodHelpUsAll( warrantyProgram.Garantiebedingungen2);
				if(Len(Trim(warrantyProgram.Garantiebedingungen2))) {
					conditions &= translation.getText("rgtext", "beiliegenden", locale) & " " & warrantyProgram.Garantiebedingungen2 & "." & Request.CRLF & Request.CRLF;
				}
			} else {
				// someFunctionGodHelpUsAll( warrantyProgram.Garantiebedingungen);
				if(Len(Trim(warrantyProgram.Garantiebedingungen))) {
					conditions &=  translation.getText("rgtext", "beiliegenden", locale) & " " & warrantyProgram.Garantiebedingungen & "." & Request.CRLF & Request.CRLF;
				}
			}
		}

		if(ListFindNoCase(contract.Fahrzeug.Merkmale, "TRA") and warrantyProgram.Annahme.GewerblicheNutzungTransporter) {
			conditions &= translation.getText("rgtext", "gewerblich", locale) & Request.CRLF & Request.CRLF;
		}
		if(warrantyProgram.Annahme.Eigenbetrieb) {
			conditions &= translation.getText("rgtext", "inspektion", locale) & Request.CRLF & Request.CRLF;
		}

		if(warrantyProgram.Annahme.BenachrichtigungGarantiegeber) {
			conditions &= translation.getText("rgtext", "schaden", locale) & Request.CRLF & Request.CRLF;
		}

		if(contract.Regulierungsbetrag && !isWarrantyProgramSlovenian()) {
			conditions = conditions & translation.getText("rgtext", "hoechst1", locale) & " "
				& application.globals.gmsPDFEscape(application.globals.gmsGetCurrencyFormat(Amount = contract.Regulierungsbetrag, Currency = warrantyProgram.Currency, IsHTMLEscaped = FALSE))
				& "." & Request.CRLF & Request.CRLF;
		}

		if(contract.RegulierungsbetragAlte) {
			//print "AlterAlte" only if due date within term
			IsPrintAlterAlte = application.globals.gmsIs(warrantyProgram.Regulierung.AlterAlte and contract.DatumAlte);
			IsPrintLaufleistungAlte = application.globals.gmsIs(warrantyProgram.Regulierung.LaufleistungAlte);
			if(IsPrintAlterAlte or IsPrintLaufleistungAlte) {
				conditions &= translation.getText("rgtext", "hoechst2", locale) & " ";
				// Alter
				if(isPrintALterAlte) {
					conditions &= translation.getText("rgtext", "hoechst3", locale) & " ";
					conditions &= warrantyProgram.Regulierung.AlterAlte & " ";
					conditions &= replaceNoCase(translation.getText("rgtext", "hoechst4", locale), "$$", translation.getProperty("calculationBase", warrantyProgram.regulierung.calculationBase, locale)) & " ";
				}
				// Verknaepfung
				if(IsPrintAlterAlte and IsPrintLaufleistungAlte) {
					conditions &=  " " & translation.getText("rgtext", "bzw", locale)  & " ";
				}
				// Laufleistung
				if(IsPrintLaufleistungAlte) {
					conditions &= translation.getText("rgtext", "hoechst5", locale) & " "
						& Trim(LSNumberFormat(warrantyProgram.Regulierung.LaufleistungAlte, "9,999,999")) & " " & translation.getText("rgtext", "hoechst6", locale);
				}
				 conditions &= translation.getText("rgtext", "hoechst7", locale) & " "
						& application.globals.gmsPDFEscape(application.globals.gmsGetCurrencyFormat(Amount = contract.RegulierungsbetragAlte, Currency = warrantyProgram.Currency, IsHTMLEscaped = FALSE))
						& "." & Request.CRLF & Request.CRLF;
			}
		}

		if(contract.RegulierungsbetragGesamt) {
			conditions &= translation.getText("rgtext", "hoechst8", locale) & " "
				& application.globals.gmsPDFEscape(application.globals.gmsGetCurrencyFormat(Amount = contract.RegulierungsbetragGesamt, Currency = warrantyProgram.Currency, IsHTMLEscaped = FALSE))
				& "." & Request.CRLF & Request.CRLF;
		}

		if(contract.Selbstbehalt or contract.Selbstbehalt2 or contract.SelbstbehaltFremdwerkstatt) {
			 conditions &= translation.getText("rgtext", "selbst1", locale) & " ";
			 if(contract.Selbstbehalt or contract.Selbstbehalt2) {
				 if(contract.Selbstbehalt and contract.garantie.laufzeit <= 12) {
				 	// laufzeitunabhaengiger Betrag
					conditions &= translation.getText("rgtext", "selbst2", locale) & " "
							& application.globals.gmsPDFEscape(application.globals.gmsGetCurrencyFormat(Amount = contract.Selbstbehalt, Currency = warrantyProgram.Currency, IsHTMLEscaped = FALSE))
							& " ";
				 } else if (contract.Selbstbehalt and contract.Selbstbehalt2 and contract.garantie.laufzeit > 12) {
					 if(contract.Selbstbehalt eq contract.Selbstbehalt2) {
						 conditions &= translation.getText("rgtext", "selbst2", locale) & " "
								 & application.globals.gmsPDFEscape(application.globals.gmsGetCurrencyFormat(Amount = contract.Selbstbehalt, Currency = warrantyProgram.Currency, IsHTMLEscaped = FALSE))
								 & " ";
					 } else {
						 // laufzeitabhaengiger Betrag
						 conditions &= translation.getText("rgtext", "selbst3", locale) & " "
								 & application.globals.gmsPDFEscape(application.globals.gmsGetCurrencyFormat(Amount = contract.Selbstbehalt, Currency = warrantyProgram.Currency, IsHTMLEscaped = FALSE))
								 & " " & translation.getText("rgtext", "selbst4", locale) & " "
								 & application.globals.gmsPDFEscape(application.globals.gmsGetCurrencyFormat(Amount = contract.Selbstbehalt2, Currency = warrantyProgram.Currency, IsHTMLEscaped = FALSE))
								 & " " & translation.getText("rgtext", "selbst5", locale) & " ";
					 }
				 }

				if(contract.SelbstbehaltFremdwerkstatt	and (contract.SelbstbehaltFremdwerkstatt is not contract.Selbstbehalt
								or contract.SelbstbehaltFremdwerkstatt is not contract.Selbstbehalt2)) {
					conditions &= translation.getText("rgtext", "bzw", locale) & " "
								& application.globals.gmsPDFEscape(application.globals.gmsGetCurrencyFormat(Amount = contract.SelbstbehaltFremdwerkstatt, Currency = warrantyProgram.Currency, IsHTMLEscaped = FALSE))
								& " " & translation.getText("rgtext", "selbst6", locale) & " ";
				}

			} else {
			 	// Fremdwerkstatt
				if(contract.SelbstbehaltFremdwerkstatt) {
			 		conditions &= translation.getText("rgtext", "selbst3", locale) & " "
							& application.globals.gmsPDFEscape(application.globals.gmsGetCurrencyFormat(Amount = contract.SelbstbehaltFremdwerkstatt, Currency = warrantyProgram.Currency, IsHTMLEscaped = FALSE))
							& " " & translation.getText("rgtext", "selbst6", locale) & " ";
			 	}
			}
			conditions &= translation.getText("rgtext", "selbst7", locale) & Request.CRLF & Request.CRLF;
		}

		// SonstigeBedingungen
		if(Len(Trim(collectOtherConditionsText(variables.currentPrintingLanguageId)))) {
			conditions &= collectOtherConditionsText(variables.currentPrintingLanguageId);

		}

		// insured mobility
		if(contract.InsuredMobility.InsuredMobility) {
			conditions &= collectMobiOtherConditionsText(variables.currentPrintingLanguageId, contract.InsuredMobility.Term);
		}

		// report for modules in contract

		if (isModulesVisibleInWP(warrantyProgram.id)) {

			queryService = new Query(datasource = "#application.dsn#");

			getWarrantyProgramDescriptions = queryService.execute(sql = "
				SELECT * FROM vtrwtmod vm JOIN modules mo
				ON  vm.hdmarkbaid =  mo.HDMARKBAID
				AND vm.MODULEID = mo.MODULEID
				where vm.HDMARKBAID = #warrantyProgram.id#
				AND vm.vtnr = #contract.id#
				AND vm.version = #contract.version#
			").getResult();

			if (getWarrantyProgramDescriptions.RecordCount neq 0) {

				for (i=1; i <= getWarrantyProgramDescriptions.RecordCount; i++){
					conditions &= getWarrantyProgramDescriptions.DESCRIPT[i] & " ";
				}
			}
		}
		if(isWarrantyProgramNetherlandsInsuriance()){
			conditions = "";
		}

		return remapts(conditions, locale);
	}