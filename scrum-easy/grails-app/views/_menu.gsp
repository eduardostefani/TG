<div class="navbar-collapse collapse">
	<ul class="nav navbar-nav navbar-right">
		<sec:ifNotLoggedIn>
			<li><g:link id="link_login" controller='login' action='auth'>
					<g:message code="app.login.label" />
				</g:link></li>
		</sec:ifNotLoggedIn>
	</ul>
	<sec:ifLoggedIn>
		<ul class="nav navbar-nav">	
			<menu:item nome="task" ativo="${'task' == params.controller}"/>
		</ul>

		<ul class="nav navbar-nav navbar-right">
			<li
				class="dropdown ${["user"].contains(controllerName) ? 'active' : ''}"
				data-dropdown="dropdown"><a id="link_user_toggle" href="#"
				class="dropdown-toggle" data-toggle="dropdown"> <strong><g:message
							code="user.label" />: </strong> <sec:username /> <span class="caret"></span>
			</a>
				<ul class="dropdown-menu"><%--

					
                        <menu:item nome="user"  icone="glyphicon glyphicon-user" ativo="${'user' == params.controller && 'editProfile' != params.action}" />
                    
                        <menu:item nome="permissionGroup" 
                        icone="glyphicon glyphicon-th" />
                    
                    <menu:item nome="appParameter"  icone="glyphicon glyphicon-th-list" />

                    --%><li class="divider"></li>

                    <%--<li class="${["user"].contains(params.controller) && params.action == 'editProfile' ? 'active' : ''}">
                        <a id="link_profile" href="${createLink(controller: 'user', action: 'editProfile')}">
                            <i class="glyphicon glyphicon-user"></i>
                            <g:message code="user.editProfile.header" />
                        </a>
                    </li>--%>

                    
					<li><g:link id="link_logout" controller='logout'>
							<i class="glyphicon glyphicon-off"></i>
							<g:message code="app.logout.label" />
						</g:link></li>
				</ul></li>
		</ul>
	</sec:ifLoggedIn>
</div>


