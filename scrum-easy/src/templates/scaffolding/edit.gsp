<%=packageName%>
<!doctype html>
<html>
	<head>

		<meta name="layout" content="main">
		<g:set var="entityName"
			value="\${message(code: '${domainClass.propertyName}.label', default: '${className}')}" />
		<g:set var="entitiesName"
			value="\${message(code: '${domainClass.propertyName}.label.plural', default: '${className}')}" />
		<title>
			<g:message code="default.edit.label" args="[entityName]" />
		</title>

	</head>
	<body>

		<bootstrap:header title="\${message(code:'default.edit.label', args:[entityName])}">

			<sec:ifAllGranted roles="ROLE_${domainClass.propertyName.toUpperCase()}_DEL">
				<bootstrap:buttonRemoveModal name="\${entityName}" id="\${${propertyName}?.id}"/>
			</sec:ifAllGranted>

			<bootstrap:buttonShow id="\${${propertyName}?.id}"/>

			<bootstrap:buttonList/>

		</bootstrap:header>

		<g:if test="\${flash.message}">
			<bootstrap:alert class="alert-info">\${flash.message}</bootstrap:alert>
		</g:if>

		<g:eachError bean="\${${propertyName}}" field="version" var="error">
			<bootstrap:alert class="alert-error">
				<g:message error="\${error}"/>
			</bootstrap:alert>
		</g:eachError>

		<g:form class="form-horizontal" action="edit" id="\${${propertyName}?.id}" <%= multiPart ? ' enctype="multipart/form-data"' : '' %>>

			<bootstrap:form title="\${message(code:'${propertyName}.form.legend')}">
				<g:hiddenField name="version" value="\${${propertyName}?.version}" />
				<f:all bean="${propertyName}"/>
			</bootstrap:form>

			<bootstrap:actionButtons>
				<button type="submit" class="btn btn-primary">
					<i class="glyphicon glyphicon-ok glyphicon glyphicon-white"></i>
					<g:message code="default.button.update.label" default="Update" />
				</button>
			</bootstrap:actionButtons>

		</g:form>

	</body>
</html>
