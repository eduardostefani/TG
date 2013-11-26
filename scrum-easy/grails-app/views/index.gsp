<!doctype html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>
			<g:message code="app.welcome.label" />
		</title>
	</head>

	<body>

		<div class="row">
			<sec:ifNotLoggedIn>
				<section id="main" class="col-md-12">
					<div class="jumbotron">
						<h1><g:message code="applicationName"/></h1>
						<p><g:message code="applicationDescription" /></p>


						<g:link class="btn btn-lg btn-info" controller='login' action='auth'>
							<g:message code="app.clickhere.label" /> »
						</g:link>
					</div>
				</section>
			</sec:ifNotLoggedIn>
			<sec:ifLoggedIn>
				<section id="main">
					<div class="row jumbotron">
						<div class="col-md-6">
							<h1><g:message code="applicationName"/></h1>
							<p><g:message code="applicationDescription" /></p>
							<g:message code="app.welcome.label" /> <sec:username/>
						</div>
					</div>
				</section>
			</sec:ifLoggedIn>
		</div>

	</body>
</html>
