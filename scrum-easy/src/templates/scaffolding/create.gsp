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
			<g:message code="default.create.label" args="[entityName]" />
		</title>

	</head>
	<body>

		<bootstrap:header title="\${message(code:'default.create.label', args:[entityName])}">

			<bootstrap:buttonList/>

		</bootstrap:header>

		<g:if test="\${flash.message}">
			<bootstrap:alert class="alert-info">\${flash.message}</bootstrap:alert>
		</g:if>

		<g:form class="form-horizontal" action="create" <%= multiPart ? ' enctype="multipart/form-data"' : '' %>>

			<bootstrap:form title="\${message(code:'${propertyName}.form.legend')}">
				<f:all bean="${propertyName}"/>
			</bootstrap:form>

			<bootstrap:actionButtons>
				<button type="submit" class="btn btn-primary">
					<i class="glyphicon glyphicon-ok glyphicon glyphicon-white"></i>
					<g:message code="default.button.create.label" default="Create" />
				</button>
			</bootstrap:actionButtons>

		</g:form>

	</body>
</html>
