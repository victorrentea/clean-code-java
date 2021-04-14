
	// TODO: abstract this method, move out filters to more generic form
	public array function queryToTree( 	query sourceQuery,
										numeric parentId,
										string checked,
										string checkedReadOnly,
										string hidden,
										numeric userTypeId = 10,
										numeric companyType = 0,
										numeric businessLine = 0,
										boolean readOnly = false,
										struct parentNode = {}) {

		var children 	= [];
		var i 			= 1;
		var qoq 		= new Query();
		qoq.setAttributes(QoQsrcTable = sourceQuery);

		var qoqResult 	= qoq.execute(sql="select * from QoQsrcTable where cast(parentId as integer) = cast(#parentId# as integer)", dbtype="query").getResult();

		for(i; i <= qoqResult.recordCount; i++) {

			var currentId 	= qoqResult["id"][i];
			var rbLabel 	= qoqResult["key"][i];
			var hiddenNode	= { "data" = "", "attr" = { "class" =  "forced-hidden" }}; // used to disable checking parent in case of limited set of children

			if(	(userTypeId gte qoqResult["userType"][i] OR userTypeId gte userPermissionExceptions.getCompanyPermissionUserTypeException(rbLabel, session.currentUser.getWorkingCompanyId()))
				and ( session.currentUser.isAuthorized(action = rbLabel, drilldown = true, drillup = true) or session.currentUser.isBackendUser())
				and listFind(qoqResult["companyType"][i], companyType)  > 0
				and listFind(hidden, currentId)  <= 0) {

				//and listFind(qoqResult["businessline"][i], businessLine)  > 0

				var tempNode						= {};
				tempNode["data"]					= variables.translation.getPermissionLabel(rbLabel);
				tempNode["metadata"]				= {};
				tempNode["metadata"]["id"]			= currentId;
				tempNode["metadata"]["dependsOn"]	= qoqResult["dependsOn"][i];
				tempNode["attr"]					= {};

				var currentNodeCheckedReadOnly 	= (listFind(checkedReadOnly, currentId)  > 0);
				var currentNodeChecked			= (listFind(checked, currentId)  > 0);

				if (currentNodeCheckedReadOnly) {
					tempNode["attr"]["rel"] = "disabledchecked";
				}

				if (currentNodeChecked) {

					if (readOnly) {
						tempNode["attr"]["rel"] = "disabledchecked";
					} else {
						tempNode["attr"]["class"] = "jstree-checked";
					}

					if (currentNodeCheckedReadOnly) {
						tempNode["attr"]["rel"] = "disabledchecked";
					}

				}

				if(readOnly and (not currentNodeCheckedReadOnly) and (not currentNodeChecked)){
					tempNode["attr"]["rel"] = "disabledunchecked";
				}

				var childrenArray = queryToTree(sourceQuery, currentId,
												 checked, checkedReadOnly, hidden,
												 userTypeId, companyType, businessLine, readOnly, tempNode );

				if(arrayLen(childrenArray) > 0) {
					tempNode["state"] 	= "open";
					tempNode["children"] = childrenArray;



					var authorizedBranch 		= ( session.currentUser.isAuthorized(action = rbLabel, drilldown = false, drillup = true)
													or
												  session.currentUser.isBackendUser() );

					if ( !authorizedBranch and !readOnly) {

						tempNode["attr"]["rel"] = "disabledunchecked"; //disable checking parent if permission is not explicitly set

						arrayAppend(tempNode["children"], hiddenNode);
					}

				}

				arrayAppend(children, tempNode);

			} else {

				if(structKeyExists(arguments.parentNode, "children"))
					arrayAppend(arguments.parentNode["children"], hiddenNode);
			}
		}

		return children;
	}
}