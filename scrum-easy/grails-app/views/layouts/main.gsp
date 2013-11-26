<%@ page
	import="org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<title><g:layoutTitle default="${meta(name: 'app.name')}" /></title>

<!-- Google Chrome Frame for IE -->
<meta http-equiv="X-UA-Compatible" content="chrome=1">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
<!--[if lt IE 9]>
			<script src="${resource(dir: 'js', file: 'html5.js')}"></script>
		<![endif]-->

<r:require modules="scaffolding, keyboard, utils, bootstrap3" />

<r:layoutResources />
<g:layoutHead />




</head>

<body>

	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
					<a class="navbar-toggle" data-toggle="collapse"
						data-target=".navbar-collapse"> <span class="icon-bar"></span>
						<span class="icon-bar"></span> <span class="icon-bar"></span>
					</a> <a class="navbar-brand" href="${createLink(uri: '/')}"> <g:message
							code="applicationName" />

					</a>

				</div>
				<g:render template="/menu" />
			</div>
	</nav>

	<div class="container" style="min-height: 600px;">
		<g:layoutBody />

		<div class="row">
			<div class="col-md-12">
				<hr>
				<footer class="pull-right">
					<p>
						<g:message code="applicationName" />
						&nbsp;-&nbsp;
						<g:message code="applicationDescription" />
						&nbsp;-&nbsp; <a
							href="${message(code: 'applicationCopyrightLink')}"> <g:message
								code="applicationCopyright" />
						</a> &nbsp;-&nbsp;
						<g:message code="applicationFrom" />
						&nbsp;/&nbsp;
						<g:formatDate date="${new Date()}" format="yyyy" />
				</footer>
			</div>
		</div>
	</div>

	<g:render template="/components/defaultModal" />

	<r:script>
			$("input[data-focus=true], textarea[data-focus=true]").first().focus();

			$("[data-mask], [data-mask-type]").each(function() {
				var element = $(this);

				var id = element.attr('id');
				var mask = element.data('mask');
				var maskType = element.data('mask-type');
				var changeId = element.data('mask-change');

				if (mask) {
					new InputMask(mask, id);
				} else if ($.inArray(maskType, ['cpf', 'cnpj', 'rg', 'pis', 'cei']) >= 0) {
					maskIdentity(id, maskType, changeId);
				} else if (maskType) {
					new InputMask(defaultMaskPatterns[maskType], id);
				}
			});

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



	<r:layoutResources />
</body>
</html>