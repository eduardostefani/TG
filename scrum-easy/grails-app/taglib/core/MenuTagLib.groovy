package core

import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

class MenuTagLib {

  static namespace = "menu"

  def item = { attrs, body ->
      if (SpringSecurityUtils.ifAllGranted(attrs.roles)) {
          // por padrao, compara o nome com o controller atual
          def liClass = attrs.nome == params.controller ? 'active' : ''

          // mas usuario pode usar uma expressao mudar esse comportamento
          if (attrs.ativo != null) {
              liClass = new Boolean(attrs.ativo) ? 'active' : ''
          }

          // por padrao label sera baseado no nome
          def label = message(code: "${attrs.nome}.label.plural")

          // mas usuario pode passar outro label se quiser
          if (attrs.label) {
              label = attrs.label
          }

          out << "<li class='${liClass}'>"
          out << "    <a id='link_${attrs.nome}' href='${createLink(controller: attrs.nome)}'>"

          if (attrs.icone) {
              out << "        <i class='${attrs.icone}'></i>"
          }

          out << "        ${label}"
          out << "    </a>"
          out << "</li>"
      }
  }

}
