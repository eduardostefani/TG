<%@ page import="scrumeasy.core.Task" %>



<div class="fieldcontain ${hasErrors(bean: taskInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="task.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${taskInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: taskInstance, field: 'priority', 'error')} required">
	<label for="priority">
		<g:message code="task.priority.label" default="Priority" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="priority" from="${scrumeasy.core.enuns.PRIORITY?.values()}" keys="${scrumeasy.core.enuns.PRIORITY.values()*.name()}" required="" value="${taskInstance?.priority?.name()}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: taskInstance, field: 'status', 'error')} required">
	<label for="status">
		<g:message code="task.status.label" default="Status" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="status" from="${scrumeasy.core.enuns.STATUS?.values()}" keys="${scrumeasy.core.enuns.STATUS.values()*.name()}" required="" value="${taskInstance?.status?.name()}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: taskInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="task.description.label" default="Description" />
		
	</label>
	<g:textField name="description" value="${taskInstance?.description}"/>
</div>

