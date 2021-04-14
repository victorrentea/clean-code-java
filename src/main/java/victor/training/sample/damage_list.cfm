...

					<cfset IsSessionUserAuthorizedOrganisation = cSessionUser.authorizedToAccessOrganisation(Company = cDamage.Company, Dealer = cDamage.Dealer) >

					<cfset authorizedToSeeDamageDetails = (cSessionUser.isAuthorized("extranet/damage/view") and not ( cSessionUser.isExtranetUser() and session.currentUser.getWorkingBusinessLine().isInsurance() )) >
			<cfif !stGaranty.Loesch and stGaranty.active>
					<cfif (cCompany.IsExpressEdition("damage") or (not cDamage.billAssigned() or not cDamage.isReleased())) and cSessionUser.isAuthorized("extranet/damage/edit") and IsSessionUserAuthorizedOrganisation>
						<a class="nounderline" href="#URLUtilities.gmsURL(URLUtilities.getScriptName(), "action=edit", "mode=edit", "company=" & cDamage.Company, "damage=" & cDamage.ID)#" title="#translation.getLabel('vs_damageEdit')#">
							<img src="#Request.URLDir#images/edit.gif" alt="#translation.getLabel('vs_damageEdit')#" width="24" height="24" border="0" />
						</a>
					<cfelseif (not cDamage.billAssigned() or not cDamage.isReleased()) and (cSessionUser.isAuthorized("extranet/damage/release") or cSessionUser.isAuthorized("extranet/damage/reject")) and IsSessionUserAuthorizedOrganisation>
						<a class="nounderline" href="#URLUtilities.gmsURL(URLUtilities.getScriptName(), "action=edit", "mode=edit", "company=" & cDamage.Company, "damage=" & cDamage.ID)#" title="#translation.getLabel('vs_damageRelease')#">
							<img src="#Request.URLDir#images/edit.gif" alt="#translation.getLabel('vs_damageRelease')#" width="24" height="24" border="0" />
						</a>
					<cfelseif (not cDamage.billAssigned() or not cDamage.isReleased()) and authorizedToSeeDamageDetails>
						<a class="nounderline" href="#URLUtilities.gmsURL(URLUtilities.getScriptName(), "action=edit", "mode=display", "company=" & cDamage.Company, "damage=" & cDamage.ID)#" title="#translation.getLabel('vs_damageView')#">
							<img src="#Request.URLDir#images/display.png" alt="#translation.getLabel('vs_damageView')#" width="24" height="24" border="0" />
						</a>
					<cfelse>
						<img src="#Request.URLDir#images/transpixel.gif" alt="" width="24" height="24" border="0" />
					</cfif>
					<cfif !stGaranty.Loesch and stGaranty.active and cDamage.accountingNotConcluded() and !cDamage.damageClosedBeforeSettlement() and cSessionUser.isAuthorized("extranet/damage/delete") and IsSessionUserAuthorizedOrganisation>
						<a class="nounderline" href="##" onclick="dynConfirm('#translation.getLabel("vs_wrongCreatedDamages")#', '#translation.getLabel("vs_damageDeleteMessage")#', '#translation.getLabel("vs_yes")#', '', '#URLUtilities.gmsURL(URLUtilities.getScriptName(), "action=deletedamage", "company=" & cDamage.Company, "damage=" & cDamage.ID)#', '#translation.getLabel("vs_no")#', '', '');" title="#translation.getLabel("vs_wrongCreatedDamages")#">
							<img src="#Request.URLDir#images/wrongschaden.png" alt="#translation.getLabel("vs_wrongCreatedDamages")#" width="24" height="24" border="0" />
						</a>
					<cfelse>
						<img src="#Request.URLDir#images/transpixel.gif" alt="" width="24" height="24" border="0" />
					</cfif>

					<cfif cCompany.checkDamageAreaAccessibility()>
						<cfif cDamage.billAssigned() and cDamage.isReleased() and cSessionUser.isAuthorized("extranet/damage/bill/edit") and isSessionUserAuthorizedOrganisation>
							<a class="nounderline" href="#URLUtilities.gmsURL(URLUtilities.getScriptName(), "action=editbill", "company=" & cDamage.Company, "damage=" & cDamage.ID)#" title="#translation.getLabel('vs_damageRegisterPosition')#">
								<img src="#Request.URLDir#images/new.gif" alt="#translation.getLabel('vs_damageRegisterPosition')#" width="24" height="24" border="0" />
							</a>
						<cfelseif cDamage.billAssigned() and cDamage.isReleased() and cSessionUser.isAuthorized("extranet/damage/bill/view")>
							<a class="nounderline" href="#URLUtilities.gmsURL(URLUtilities.getScriptName(), "action=editbill", "company=" & cDamage.Company, "damage=" & cDamage.ID)#" title="#translation.getLabel('vs_damageBillView')#">
								<img src="#Request.URLDir#images/display.gif" alt="#translation.getLabel('vs_damageBillView')#" width="24" height="24" border="0" />
							</a>
						<cfelse>
							<img src="#Request.URLDir#images/transpixel.gif" alt="#translation.getLabel('vs_damageBill')#" width="24" height="24" border="0" />
						</cfif>
					</cfif>
			</cfif>

...