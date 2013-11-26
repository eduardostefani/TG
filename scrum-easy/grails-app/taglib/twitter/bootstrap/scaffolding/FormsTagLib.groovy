package twitter.bootstrap.scaffolding

import org.springframework.validation.Errors
import org.springframework.validation.FieldError

class FormsTagLib {

	static namespace = "bootstrap"

	def fieldError = { attrs ->

		def bean = attrs.bean
		def field = attrs.field
		Errors errors = bean?.errors
		FieldError error = errors?.getFieldError(field)
		out << g.hasErrors(bean: bean, field: field) {
			out << '<span class="help-inline">'
			out << g.message(error: error)
			out << '</span>'
		}
	}

	def header = { attrs, body ->

		def title = attrs.title
		out << '<div class="row">'
	    out <<   '<div class="col-md-6">'
	    out <<     '<h1>'<< title <<'</h1>'
	    out <<   '</div>'
	    out <<   '<div class="col-md-6">'
	    out <<     '<div class="btn-group pull-right">'
	    out <<			body()
	    out <<     '</div>'
	    out <<   '</div>'
	    out << '</div>'
	    out << '<hr/>'
	}

	def alert = { attrs, body ->
		out << '<div class="row">'
        out <<   '<div class="col-md-12">'
		out <<     '<div class="alert alert-block ' << attrs.class.tokenize().join(" ") << '">'
		out <<       '<a class="close" data-dismiss="alert">&times;</a>'
		out <<       '<p>' << body() << '</p>'
		out <<     '</div>'
		out <<    '</div>'
		out << '</div>'
	}


	def actionButtons = { attrs, body ->
		out << '<div class="col-md-12 jumbotron">'
        out << 	 '<div class="col-md-4">'
        out <<     body()
        out <<   '</div>'
        out << '</div>'
	}

	def form = { attrs, body ->
		out << '<div class="row">'
        out <<   '<div class="col-md-12">'
        out <<     '<div class="panel panel-default">'
        out <<       '<div class="panel-heading">'
        out <<         attrs.title
        out <<       '</div>'
        out <<       '<div class="panel-body">'
        out <<         body()
        out <<       '</div>'
        out <<     '</div>'
        out <<   '</div>'
      	out << '</div>'
	}

	def buttonCreate = { attrs, body ->
		out << '<a href="' << g.createLink(action: "create") <<'" class="btn btn-success">'
        out << 	 '<i class="glyphicon glyphicon-plus glyphicon glyphicon-white"></i>&nbsp;'
        out <<    g.message(code:"default.button.create.label", default:"Create")
      	out << '</a>'
	}

	def buttonList = { attrs, body ->
		out << '<a href="' << g.createLink(action: "list") <<'" class="btn btn-default">'
        out << 	 '<i class="glyphicon glyphicon-list"></i>&nbsp;'
        out <<   g.message(code:"default.button.list.label", default:"List")
      	out << '</a>'
	}

	def buttonEdit = { attrs, body ->

		out << '<a href="' << g.createLink(action: "edit", id: attrs.id) <<'" class="btn btn-info '<< attrs.css <<'">'
        out << 	 '<i class="glyphicon glyphicon-pencil glyphicon glyphicon-white"></i>&nbsp;'
        out <<   g.message(code:"default.button.edit.label", default:"Edit")
      	out << '</a>'
	}

    def buttonShow = { attrs, body ->

        out << '<a href="' << g.createLink(action: "show", id: attrs.id) <<'" class="btn btn-default '<< attrs.css <<'">'
        out <<   '<i class="glyphicon glyphicon-zoom-in glyphicon glyphicon-white"></i>&nbsp;'
        out <<   g.message(code:"default.button.show.label", default:"Show")
        out << '</a>'
    }

	def buttonRemoveModal = {attrs, body ->

		def dataHeader = g.message(code:"default.confirm.delete.header", args: [attrs.name])
		def dataMessage = g.message(code:"default.confirm.delete.message", args: [attrs.name, attrs.id])

		out << '<a href="#" id="remove_${attrs.id}"'
		out <<	  'class="btn btn-danger '<< attrs.css << '"'
		out <<	  'data-controls-modal="defaultModal"'
		out <<	  'data-backdrop="true"'
		out <<	  'data-keyboard="true"'
		out <<	  'data-toggle="modal"'
		out <<	  'data-target="#defaultModal"'
		out <<	  'data-header="' << dataHeader << '"'
		out <<	  'data-message="' << dataMessage << '"'
		out <<	  'data-label="' << g.message(code: "default.button.delete.label", default: "Delete") << '"'
		out <<	  'data-url="' << g.createLink(action: "delete", id: attrs.id) << '">'
	    out <<  '<i class="glyphicon glyphicon-trash glyphicon glyphicon-white"></i>&nbsp;'
	    out <<   g.message(code:"default.button.delete.label", default:"Delete")
	    out << '</a>'
    }
}
