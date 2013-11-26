<!doctype html>
<html>
<head>
	<meta name='layout' content='clean' />
	<title><g:message code="applicationName" />: <g:message code="app.login.label" /></title>
</head>

<body>



	<div class="row" style="padding-top: 25px;">
		<div class="col-md-6 col-md-offset-3">
			<g:if test="${flash.message}">
				<div>
					<bootstrap:alert class="alertr alert-info">${flash.message}</bootstrap:alert>
				</div>
			</g:if>
		</div>
	</div>

	<div class="container">
		<form action='${postUrl}' method='POST' id='loginForm' class="form-signin" role="form" autocomplete='off'>

			<h2 class="form-signin-heading"><g:message code="app.login.label" /></h2>
			<input type="text"  name='j_username' id='username' class="form-control" placeholder="${message(code:'app.username.label')}" autofocus >
        	<input type="password" name='j_password' id='password' class="form-control" placeholder="${message(code:'app.password.label')}" >

        	<label class="checkbox">
				<input type='checkbox' class='chk' name='${rememberMeParameter}' id='remember_me'
				<g:if test='${hasCookie}'>checked='checked'</g:if> />

				<g:message code="app.rememberme.label" />
				&nbsp;|&nbsp;
				<a href="${createLink(controller: 'register', action: 'forgotPassword')}">
					<g:message code="spring.security.ui.login.forgotPassword" />
				</a>
			</label>

			<button id="btnReset" class="btn btn-lg btn-primary btn-block" type="submit">
				<g:message code="app.login.label" />
			</button>
		</form>
	</div>
</body>
</html>
