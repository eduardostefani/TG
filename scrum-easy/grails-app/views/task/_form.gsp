<%@ page import="scrumeasy.core.Task" %>



<div class="fieldcontain ${hasErrors(bean: taskInstance, field: 'descricao', 'error')} ">
	<label for="descricao">
		<g:message code="task.descricao.label" default="Descricao" />
		
	</label>
	<g:textField name="descricao" value="${taskInstance?.descricao}"/>
</div>

