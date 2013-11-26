<%@ page import="scrumeasy.core.Task"%>
<!doctype html>
<html>
<head>

<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'task.label', default: 'Task')}" />
<g:set var="entitiesName"
	value="${message(code: 'task.label.plural', default: 'Task')}" />

<title><g:message code="default.create.label"
		args="[entityName]" /></title>

</head>
<body>

	<bootstrap:header
		title="${message(code:'default.create.label', args:[entityName])}">

		<bootstrap:buttonList />

	</bootstrap:header>

	<g:if test="${flash.message}">
		<bootstrap:alert class="alert-info">
			${flash.message}
		</bootstrap:alert>
	</g:if>

	<g:form class="form-horizontal" action="create">
		<div class="row">
			<div class="col-md-12">
				<div class="panel panel-default " style="min-height: 300px;">
					<div class="panel-heading">
						<g:message code="task.form.legend" />
					</div>
					<div class="panel-body">
						<f:field bean="taskInstance" property="name" />
						<f:field bean="taskInstance" property="priority" />
						<f:field bean="taskInstance" property="status" />
						<f:field bean="taskInstance" property="description" />
					</div>
				</div>
			</div>
		</div>

		<bootstrap:actionButtons>
			<button type="submit" class="btn btn-primary">
				<i class="glyphicon glyphicon-ok glyphicon glyphicon-white"></i>
				<g:message code="default.button.create.label" default="Create" />
			</button>
		</bootstrap:actionButtons>

	</g:form>

</body>
</html>
