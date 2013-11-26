
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
			<g:message code="default.list.label" args="[entitiesName]" />
		</title>

	</head>
	<body>

		<bootstrap:header title="${message(code:'default.list.label', args:[entitiesName])}">
			<sec:ifAllGranted roles="ROLE_TASK_ADD">
				<bootstrap:buttonCreate/>
			</sec:ifAllGranted>

		</bootstrap:header>

		<g:if test="${flash.message}">
			<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
		</g:if>

		<div class="col-md-12">
			<c:renderFilter focus="true" />
		</div>

		<g:if test="${taskInstanceTotal > 0}">
			<div class="row">
				<div class="col-md-12">
					<table class="table table-striped">
						<thead>
							<tr>
							
								<g:sortableColumn property="name" title="${message(code: 'task.name.label', default: 'Name')}" params="[filter: params.filter]" />
							
								<g:sortableColumn property="priority" title="${message(code: 'task.priority.label', default: 'Priority')}" params="[filter: params.filter]" />
							
								<g:sortableColumn property="status" title="${message(code: 'task.status.label', default: 'Status')}" params="[filter: params.filter]" />
							
								<%--<g:sortableColumn property="description" title="${message(code: 'task.description.label', default: 'Description')}" params="[filter: params.filter]" />--%>
								
								<th></th>
							</tr>
						</thead>
						<tbody>
						<g:each in="${taskInstanceList}" var="taskInstance">
							<tr>
							
								<td>${fieldValue(bean: taskInstance, field: "name")}</td>
							
								<td>${fieldValue(bean: taskInstance, field: "priority")}</td>
							
								<td>${fieldValue(bean: taskInstance, field: "status")}</td>

								<%--<td>${fieldValue(bean: taskInstance, field: "description")}</td>--%>
								
								<td class="link">
									<div class="btn-group pull-right">
										<sec:ifAllGranted roles="ROLE_TASK_DEL">
											<bootstrap:buttonRemoveModal name="${entityName}"
												id="${taskInstance?.id}" css="btn-sm hidden-sm"/>
										</sec:ifAllGranted>
										<sec:ifAllGranted roles="ROLE_TASK_EDIT">
											<bootstrap:buttonEdit id="${taskInstance?.id}" css="btn-sm hidden-sm"/>
										</sec:ifAllGranted>
										<bootstrap:buttonShow id="${taskInstance?.id}" css="btn-sm"/>

									</div>
								</td>
							</tr>
						</g:each>
						</tbody>
					</table>
				</div>
			</div>

			<div class="row">
				<div class="col-md-12">
					<div class="pagination">
						<bootstrap:paginate total="${taskInstanceTotal}"  params="[filter: params.filter]" />
					</div>
				</div>
			</div>
		</g:if>

		<g:else>
			<bootstrap:alert class="alert alert-warning">
        <strong>
          <g:message code="default.none.message" args="[entityName]" />
        </strong>
      </bootstrap:alert>
		</g:else>

	</body>
</html>
