package core;

import groovy.time.TimeCategory
import groovy.time.TimeDuration

import org.springframework.web.servlet.support.RequestContextUtils as RCU

class CoreTagLib {

  def grailsAttributes

  static namespace = "c"

  def renderFilter = { attrs, body ->
    out << "<div class='row' style='margin-top: 15px; margin-bottom: 15px;'>"
    out << "<form method='POST' action='${createLink(action: attrs.action ?: 'list', params: [sort: params.sort ?: '', order: params?.order ?: ''])}'>"
    out << "  <div class='input-group col-lg-6'>"
    out << "    <input class='form-control' id='filterInput' name='filter' placeholder='${message(code: 'default.button.filter.label')}' value='${params.filter ?: ''}'  ${attrs.focus == 'true' ? "data-focus=\'true\'" : ''} data-shortcut='${message(code: 'default.shortcut.filter')}' type='text'/>"
    out << "    <span class='input-group-btn'>"
    out << "    <button type='submit' class='btn btn-info'><i class='glyphicon glyphicon-search'></i></button>"
    out << "    </span"
    out << "  </div>"
    out << body()
    out << "</form>"
    out << "</div>"
  }

  private def precisionToInt = { p ->
    switch (p) {
      case "years": return 100;
      case "months": return 200;
      case "days": return 300;
      case "hours": return 400;
      case "minutes": return 500;
      case "seconds": return 600;
      case "milliseconds": return 700;
      default: return -1;
    }
  }

  private def formatDuration = { td, precision ->
    def parts = []
    def precisionIndex = precisionToInt(precision)

    if (td.years && (precisionToInt("years") <= precisionIndex)) {
      parts << "${td.years} ${td.years > 1 ? 'anos' : 'ano'}"
    }

    if (td.months && (precisionToInt("months") <= precisionIndex)) {
      parts << "${td.months} ${td.months > 1 ? 'meses' : 'mes'}"
    }

    if (td.days && (precisionToInt("days") <= precisionIndex)) {
      parts << "${td.days} ${td.days > 1 ? 'dias' : 'dia'}"
    }

    if (td.hours && (precisionToInt("hours") <= precisionIndex)) {
      parts << "${td.hours} ${td.hours > 1 ? 'horas' : 'hora'}"
    }

    if (td.minutes && (precisionToInt("minutes") <= precisionIndex)) {
      parts << "${td.minutes} ${td.minutes > 1 ? 'minutos' : 'minuto'}"
    }

    if (td.seconds && (precisionToInt("seconds") <= precisionIndex)) {
      parts << "${td.seconds} ${td.seconds > 1 ? 'segundos' : 'segundo'}"
    }

    if (td.millis && (precisionToInt("millis") <= precisionIndex)) {
      parts << "${td.millis} ${td.millis > 0 ? 'milisegundos' : 'milisegundo'}"
    }

    return parts.join(", ")
  }


  def formatMilliseconds = { attrs ->
    def millis = attrs.milliseconds
    def precision = attrs.precision ?: "minutes"

    Date now = new Date();
    TimeDuration td = null
    use (TimeCategory) {
      td = new Date(millis + now.time) - now
    }

    out << formatDuration(td, precision)
  }

  def formatMillisecondsInHours = {attrs ->
    def millis = attrs.milliseconds as Long

    def totalMinutes = millis / 1000 /60
    def hours = (totalMinutes as Long) / 60 as Integer
    def minutes = (totalMinutes as Long) % 60

    out << "${hours}:${minutes}"
  }

  def formatInterval = { attrs ->
    def start = attrs.start
    def end = attrs.end
    def precision = attrs.precision ?: "minutes"

    TimeDuration td = null
    use (TimeCategory) {
      td = end - start
    }

    out << formatDuration(td, precision)
  }

  def format = { attrs ->
    def type = attrs.type
    def doc = attrs.doc

    switch(type) {
      case "cpf":
        out << "${formatCpf(cpf: doc)}"
      break
      case "cnpj":
        out << "${formatCnpj(cnpj: doc)}"
      break
      case "rg":
        out << "${formatRg(rg: doc)}"
      break
      case "cei":
        out << "${formatCei(cei: doc)}"
      break
      case "pis":
        out << "${formatPis(pis: doc)}"
      break
    }

  }

  def formatCpf = { attrs, body ->
    def doc = attrs.cpf
    if(doc?.size() == 11){
      out << doc.substring(0, 3) + '.' + doc.substring(3, 6) + '.'
      out << doc.substring(6, 9) + '-' + doc.substring(9, 11)
    } else {
      out << doc
    }
  }

  def formatCnpj = { attrs, body ->
    def doc = attrs.cnpj
    if(doc?.size() == 14){
      out << doc.substring(0, 2) + '.' + doc.substring(2, 5) + '.'
      out << doc.substring(5, 8) + '/' + doc.substring(8, 12) + '-' + doc.substring(12, 14)
    } else {
      out << doc
    }
  }

  def formatRg = { attrs, body ->
    def doc = attrs.rg
    if (doc?.size() == 9) {
      out << doc.substring(0, 2) + "." + doc.substring(2, 5) + "." + doc.substring(5, 8) + "-"
      out <<  doc.substring(8, 9)
    } else {
      out << doc
    }
  }

  def formatPis = { attrs, body ->
    def doc = attrs.pis
    if (doc?.size() == 11) {
      out << doc.substring(0, doc.size() - 1) + "-" + doc[doc.size() - 1]
    } else {
      out << doc
    }
  }

  /**
   * TODO nao sabemos a formatacao correta para CEI
   */
  def formatCei = { attrs, body ->
    def doc = attrs.cei
    if (doc?.size() == 12) {
      out << doc
    } else {
      out << doc
    }
  }

  def formatCep = { attrs, body ->
    def cep = attrs.cep
    if (cep?.size() == 8) {
      out << cep.substring(0, 2) + "." + cep.substring(2, 5) + "-"
      out << cep.substring(5, 8)
    } else {
      out << cep
    }
  }

  def formatPhone = { attrs, body ->
    def phone = attrs.phone
    if(phone?.size() == 10){
      out << "(" + phone.substring(0, 2) + ") " + phone.substring(2, 6) + '-' + phone.substring(6, 10)
    } else {
      out << phone
    }
  }

  def loggedInUserInfo = { attrs, body ->
    def user = scrumeasy.core.auth.User.findByUsername(sec.loggedInUserInfo(field: "username"))
    out << (user && attrs.field ? user."${attrs.field}" : "")
  }

}