/*
 *	GRAILS APP CONFIGURATIONS
 */

grails.project.groupId = appName
grails.mime.file.extensions = true
grails.mime.use.accept.header = false
grails.mime.types = [ html: ['text/html','application/xhtml+xml'],
                      xml: ['text/xml', 'application/xml'],
                      text: 'text/plain',
                      js: 'text/javascript',
                      rss: 'application/rss+xml',
                      atom: 'application/atom+xml',
                      css: 'text/css',
                      csv: 'text/csv',
                      all: '*/*',
                      json: ['application/json','text/json'],
                      form: 'application/x-www-form-urlencoded',
                      multipartForm: 'multipart/form-data'
                    ]
grails.views.default.codec = "none" // none, html, base64
grails.views.gsp.encoding = "UTF-8"
grails.converters.encoding = "UTF-8"

grails.views.gsp.sitemesh.preprocess = true
grails.scaffolding.templates.domainSuffix = ''

grails.json.legacy.builder = false
grails.enable.native2ascii = true
grails.logging.jul.usebridge = true
grails.spring.bean.packages = []
grails.views.javascript.library="jquery"

// What URL patterns should be processed by the resources plugin
grails.resources.adhoc.patterns = ['/images/*', '/css/*', '/js/*', '/plugins/*']

// External configuration locations
grails.config.locations = [
	"file:~/.${appName}/${appName}.groovy",
	"file:~/.${appName}/${appName}.properties",
	"file:C:/.${appName}/${appName}.groovy",
	"file:C:/.${appName}/${appName}.properties",
]



/*
 *	LOG4J CONFIGURATIONS
 */

log4j = { root ->

	root.level = org.apache.log4j.Level.DEBUG

	error  'org.codehaus.groovy.grails.web.servlet',  //  controllers
		   'org.codehaus.groovy.grails.web.pages', //  GSP
		   'org.codehaus.groovy.grails.web.sitemesh', //  layouts
		   'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
		   'org.codehaus.groovy.grails.web.mapping', // URL mapping
		   'org.codehaus.groovy.grails.commons', // core / classloading
		   'org.codehaus.groovy.grails.plugins', // plugins
		   'org.codehaus.groovy.grails.orm.hibernate', // hibernate integration
		   'org.springframework',
		   'org.hibernate',
		   'net.sf.ehcache.hibernate',
		   "org.grails.plugin.resource"

	warn   'org.mortbay.log'
}

// Spring Security Core configuration:
grails.plugins.springsecurity.userLookup.userDomainClassName = 'scrumeasy.core.auth.User'
grails.plugins.springsecurity.userLookup.authorityJoinClassName = 'scrumeasy.core.auth.UserRole'
grails.plugins.springsecurity.authority.className = 'scrumeasy.core.auth.Role'


grails.plugins.springsecurity.ui.forgotPassword.emailBody='''\
Olá $user.userRealName,<br/>
<br/>
Você (ou alguem querendo se passar por você) solicitou que sua senha de acesso foi redefinida.<br/>
<br/>
Se você não fez essa solicitação, apenas ignore esse email; nenhum mudança foi feita.<br/>
<br/>
Se você fez essa solicitação, então clique <a href="$url">aqui</a> para redefinir sua senha.
'''
grails.plugins.springsecurity.ui.forgotPassword.emailFrom="eduardosstefani@gmail.com"
grails.plugins.springsecurity.ui.forgotPassword.emailSubject='Redefinir Senha de Acesso'
grails.plugins.springsecurity.ui.forgotPassword.postResetUrl=null

grails.plugins.springsecurity.errors.login.disabled = "Sorry, your account is disabled."
grails.plugins.springsecurity.errors.login.expired = "Sorry, your account is expired."
grails.plugins.springsecurity.errors.login.passwordExpired = "Sorry, your account password expired."
grails.plugins.springsecurity.errors.login.locked = "Sorry, your account is locked."
grails.plugins.springsecurity.errors.login.fail = "Sorry, we could not to find your user or/and password."

// Bootstrap configuration
grails.plugins.twitterbootstrap.fixtaglib = true


/*
 *	ENVIRONMENTS CONFIGURATIONS
 */

environments {

	production {

		//grails.serverURL = "http://SERVERIP:SERVERPORT/${appName}"

		grails {
			mail {
			  host = "smtp.gmail.com"
			  port = 465
			  username = "xxx"
			  password = "xxx"
			  props = ["mail.smtp.auth":"true",
					   "mail.smtp.socketFactory.port":"465",
					   "mail.smtp.socketFactory.class":"javax.net.ssl.SSLSocketFactory",
					   "mail.smtp.socketFactory.fallback":"false"]
			}
		}

		grails.mail.default.from="xxx"
		grails.mail.disabled=true

		log4j = {
			info	"grails.app.controllers",
					"grails.app.domain",
					"grails.app.service",
					"grails.app.conf"

			appenders {
				rollingFile(name: "stdout", maxFileSize: '10MB', file: "/opt/${appName}/logs/${appName}.log", layout: pattern(conversionPattern: "%-5p %d{dd-MM-yyyy HH:mm:ss} [%c{1}]: %m%n"))
				rollingFile(name: "StackTrace", maxFileSize: '10MB', file: "/opt/${appName}/logs/${appName}.log-stacktrace.log")
				rollingFile(name: "stdout", maxFileSize: '10MB', file: "C:/opt/${appName}/logs/${appName}.log", layout: pattern(conversionPattern: "%-5p %d{dd-MM-yyyy HH:mm:ss} [%c{1}]: %m%n"))
				rollingFile(name: "StackTrace", maxFileSize: '10MB', file: "C:/opt/${appName}/logs/${appName}.log-stacktrace.log")
			}
		}
	}

  development {

		grails.serverURL = "http://localhost:8080/${appName}"

		grails {
			mail {
			  host = "smtp.gmail.com"
			  port = 465
			  username = "xxx"
			  password = "xxx"
			  props = ["mail.smtp.auth":"true",
					   "mail.smtp.socketFactory.port":"465",
					   "mail.smtp.socketFactory.class":"javax.net.ssl.SSLSocketFactory",
					   "mail.smtp.socketFactory.fallback":"false"]
			}
		}

		grails.mail.default.from="eduardosstefani@gmail.com"
		grails.mail.disabled=true

		log4j = {
			debug	"grails.app.controllers",
					"grails.app.domains",
					//"grails.app.services",
					"grails.app.conf",
					//"grails.app.taglib.core.FeatureTagLib"

			/* Other useful Logs
			 * Quartz: "grails.app.task"
			 * Resources: "org.grails.plugin.resource"
			 */

			appenders {
				console(name:"stdout", layout: pattern(conversionPattern: "%-5p %d{dd-MM-yyyy HH:mm:ss} [%c{1}]: %m%n"))
			}
		}
  }

  test {
		log4j = { root ->
			root.level = org.apache.log4j.Level.DEBUG

			debug	"grails.app.controllers",
					"grails.app.domains",
					"grails.app.services",

			appenders {
				console(name:"stdout", layout: pattern(conversionPattern: "%-5p %d{dd-MM-yyyy HH:mm:ss} [%c{1}]: %m%n"))
			}
		}
  }
}


