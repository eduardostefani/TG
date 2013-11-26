<% import grails.persistence.Event %>
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
			<g:message code="default.list.label" args="[entitiesName]" />
		</title>

	</head>
	<body>

		<bootstrap:header title="\${message(code:'default.list.label', args:[entitiesName])}">
			<sec:ifAllGranted roles="ROLE_${domainClass.propertyName.toUpperCase()}_ADD">
				<bootstrap:buttonCreate/>
			</sec:ifAllGranted>

		</bootstrap:header>

		<g:if test="\${flash.message}">
			<bootstrap:alert class="alert-info">\${flash.message}</bootstrap:alert>
		</g:if>

		<div class="col-md-12">
			<c:renderFilter focus="true" />
		</div>

		<g:if test="\${${propertyName}Total > 0}">
			<div class="row">
				<div class="col-md-12">
					<table class="table table-striped">
						<thead>
							<tr>
							<%  excludedProps = Event.allEvents.toList() << 'id' << 'version'
								allowedNames = domainClass.persistentProperties*.name << 'dateCreated' << 'lastUpdated'
								props = domainClass.properties.findAll { allowedNames.contains(it.name) && !excludedProps.contains(it.name) && it.type != null && !Collection.isAssignableFrom(it.type) }
								Collections.sort(props, comparator.constructors[0].newInstance([domainClass] as Object[]))
								props.eachWithIndex { p, i ->
									if (i < 6) {
										if (p.isAssociation()) { %>
								<th class="header"><g:message code="${domainClass.propertyName}.${p.name}.label" default="${p.naturalName}" /></th>
							<%    } else { %>
								<g:sortableColumn property="${p.name}" title="\${message(code: '${domainClass.propertyName}.${p.name}.label', default: '${p.naturalName}')}" params="[filter: params.filter]" />
							<%  }   }   } %>
								<th></th>
							</tr>
						</thead>
						<tbody>
						<g:each in="\${${propertyName}List}" var="${propertyName}">
							<tr>
							<% props.eachWithIndex { p, i ->
									 if (i < 6) {
										 if (p.type == Boolean || p.type == boolean) { %>
								<td><g:formatBoolean boolean="\${${propertyName}.${p.name}}" /></td>
							<%     } else if (p.type == Date || p.type == java.sql.Date || p.type == java.sql.Time || p.type == Calendar) { %>
								<td><g:formatDate date="\${${propertyName}.${p.name}}" /></td>
							<%     } else { %>
								<td>\${fieldValue(bean: ${propertyName}, field: "${p.name}")}</td>
							<%  }   }   } %>
								<td class="link">
									<div class="btn-group pull-right">
										<sec:ifAllGranted roles="ROLE_${domainClass.propertyName.toUpperCase()}_DEL">
											<bootstrap:buttonRemoveModal name="\${entityName}"
												id="\${${propertyName}?.id}" css="btn-sm hidden-sm"/>
										</sec:ifAllGranted>
										<sec:ifAllGranted roles="ROLE_${domainClass.propertyName.toUpperCase()}_EDIT">
											<bootstrap:buttonEdit id="\${${propertyName}?.id}" css="btn-sm hidden-sm"/>
										</sec:ifAllGranted>
										<bootstrap:buttonShow id="\${${propertyName}?.id}" css="btn-sm"/>

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
						<bootstrap:paginate total="\${${propertyName}Total}"  params="[filter: params.filter]" />
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
