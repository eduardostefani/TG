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
			<g:message code="default.show.label" args="[entityName]" />
		</title>

	</head>
	<body>

		<bootstrap:header title="\${message(code:'default.show.label', args:[entityName])}">

			<sec:ifAllGranted roles="ROLE_${domainClass.propertyName.toUpperCase()}_ADD">
				 <bootstrap:buttonCreate/>
			</sec:ifAllGranted>

			<sec:ifAllGranted roles="ROLE_${domainClass.propertyName.toUpperCase()}_DEL">
				<bootstrap:buttonRemoveModal name="\${entityName}" id="\${${propertyName}?.id}"/>
			</sec:ifAllGranted>

			<sec:ifAllGranted roles="ROLE_${domainClass.propertyName.toUpperCase()}_EDIT">
				<bootstrap:buttonEdit id="\${${propertyName}?.id}"/>
			</sec:ifAllGranted>

			<bootstrap:buttonList/>

		</bootstrap:header>

		<g:if test="\${flash.message}">
			<bootstrap:alert class="alert-info">\${flash.message}</bootstrap:alert>
		</g:if>

		<bootstrap:form title="\${message(code:'${propertyName}.form.legend', args:[entityName])}">

			<dl class="dl-horizontal">

			<%  excludedProps = Event.allEvents.toList() << 'id' << 'version'
				allowedNames = domainClass.persistentProperties*.name << 'dateCreated' << 'lastUpdated'
				props = domainClass.properties.findAll { allowedNames.contains(it.name) && !excludedProps.contains(it.name) }
				Collections.sort(props, comparator.constructors[0].newInstance([domainClass] as Object[]))
				props.each { p -> %>
				<dt><g:message code="${domainClass.propertyName}.${p.name}.label" default="${p.naturalName}" />:</dt>
				<%  if (p.isEnum()) { %>
				<dd><g:fieldValue bean="\${${propertyName}}" field="${p.name}"/></dd>
				<%  } else if (p.oneToMany || p.manyToMany) { %>
				<g:if test="\${!${propertyName}.${p.name}}">
				<dd></dd>
				</g:if>
				<g:each in="\${${propertyName}.${p.name}}" var="${p.name[0]}">
				<dd><g:link controller="${p.referencedDomainClass?.propertyName}" action="show" id="\${${p.name[0]}.id}">\${${p.name[0]}?.encodeAsHTML()}</g:link></dd>
				</g:each>
				<%  } else if (p.manyToOne || p.oneToOne) { %>
				<dd><g:link controller="${p.referencedDomainClass?.propertyName}" action="show" id="\${${propertyName}?.${p.name}?.id}">\${${propertyName}?.${p.name}?.encodeAsHTML()}</g:link></dd>
				<%  } else if (p.type == Boolean || p.type == boolean) { %>
				<dd><g:formatBoolean boolean="\${${propertyName}?.${p.name}}" /></dd>
				<%  } else if (p.type == Date || p.type == java.sql.Date || p.type == java.sql.Time || p.type == Calendar) { %>
				<dd><g:formatDate date="\${${propertyName}?.${p.name}}" /></dd>
				<%  } else if(!p.type.isArray()) { %>
				<dd><g:fieldValue bean="\${${propertyName}}" field="${p.name}"/></dd>
				<%  } %>
			<%  } %>

			</dl>
		</bootstrap:form>

	</body>
</html>
