<%@ page import="org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes" %>
<!DOCTYPE html>
<html lang="pt-br">
	<head>
		<title><g:layoutTitle default="${meta(name: 'app.name')}"/></title>

		<!-- Google Chrome Frame for IE -->
		<meta http-equiv="X-UA-Compatible" content="chrome=1">
		<meta charset="utf-8">
		<meta name="viewport" content="initial-scale = 1.0">

		<!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
		<!--[if lt IE 9]>
			<script src="${resource(dir: 'js', file: 'html5.js')}"></script>
		<![endif]-->

		<r:require modules="scaffolding, keyboard, bootstrap3, signin3"/>

		<g:layoutHead/>
		<r:layoutResources/>

		<style type="text/css">
		body {
			padding-top: 80px;
		}
		</style>
	</head>

	<body>
		<div class="container">
			<g:layoutBody/>
		</div>

		<r:script>
			$("input[data-focus=true]").first().focus();

			$("[data-shortcut]").each(function() {
				var element = $(this);
				if (element.is('input')) {
					jwerty.key(element.data('shortcut'), function() {element.focus()});
				} else if (element.is('a, button')) {
					jwerty.key(element.data('shortcut'), function() {
						element.click();
						if (element.attr('href')) {
							document.location.href=element.attr('href')
						}
					});
				}
			});
		</r:script>

		<r:layoutResources/>

	</body>
</html>