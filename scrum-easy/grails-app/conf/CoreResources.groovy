modules = {

	scaffolding {
	    //dependsOn 'bootstrap'
	    resource url: [dir: 'css', file: 'scaffolding.css']
  	}


	keyboard {
		dependsOn 'jquery'
		resource url: [dir: 'js', file: 'jwerty.js']
	}

	utils {
		dependsOn 'jquery'
		dependsOn 'javascriptools'
		resource url: [dir: 'js', file: 'utils.js']
	}

	javascriptools {
		resource url: [dir: 'js/javascriptools', file: 'JavaScriptUtil.js']
		resource url: [dir: 'js/javascriptools', file: 'Parsers.js']
		resource url: [dir: 'js/javascriptools', file: 'InputMask.js']
	}

	bootstrap3 {
		resource url: [dir: 'css/bootstrap3', file: 'bootstrap.css']
		resource url: [dir: 'css/bootstrap3', file: 'bootstrap-theme.css']
		resource url: [dir: 'js/bootstrap3', file: 'bootstrap.js']
	}

	signin3{
		resource url: [dir: 'css/bootstrap3', file: 'signin.css']
	}

	docs3{
		resource url: [dir: 'css/bootstrap3', file: 'docs.css']
	}
}