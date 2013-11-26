
<%@ page import="scrumeasy.core.Task" %>
<!doctype html>
<html>
	<head>

		<meta name="layout" content="main">
		<g:set var="entityName"
			value="${message(code: 'task.label', default: 'Task')}" />
		<g:set var="entitiesName"
			value="${message(code: 'task.label.plural', default: 'Task')}" />
		<title>
			<g:message code="default.show.label" args="[entityName]" />
		</title>

	</head>
	<body>

		<bootstrap:header title="${message(code:'default.show.label', args:[entityName])}">

			<sec:ifAllGranted roles="ROLE_TASK_ADD">
				 <bootstrap:buttonCreate/>
			</sec:ifAllGranted>

			<sec:ifAllGranted roles="ROLE_TASK_DEL">
				<bootstrap:buttonRemoveModal name="${entityName}" id="${taskInstance?.id}"/>
			</sec:ifAllGranted>

			<sec:ifAllGranted roles="ROLE_TASK_EDIT">
				<bootstrap:buttonEdit id="${taskInstance?.id}"/>
			</sec:ifAllGranted>

			<bootstrap:buttonList/>

		</bootstrap:header>

		<g:if test="${flash.message}">
			<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
		</g:if>

		<bootstrap:form title="${message(code:'task.form.legend', args:[entityName])}">

			<dl class="dl-horizontal">

			
				<dt><g:message code="task.name.label" default="Name" />:</dt>
				
				<dd><g:fieldValue bean="${taskInstance}" field="name"/></dd>
				
			
				<dt><g:message code="task.priority.label" default="Priority" />:</dt>
				
				<dd><g:fieldValue bean="${taskInstance}" field="priority"/></dd>
				
			
				<dt><g:message code="task.status.label" default="Status" />:</dt>
				
				<dd><g:fieldValue bean="${taskInstance}" field="status"/></dd>
				
			
				<dt><g:message code="task.description.label" default="Description" />:</dt>
				
				<dd><g:fieldValue bean="${taskInstance}" field="description"/></dd>
			</dl>
		</bootstrap:form>

	</body>
</html>
