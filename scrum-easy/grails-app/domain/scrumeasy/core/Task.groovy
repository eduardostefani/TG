package scrumeasy.core

import org.grails.datastore.gorm.finders.MethodExpression.InList;

import scrumeasy.core.auth.User;
import scrumeasy.core.enuns.PRIORITY;
import scrumeasy.core.enuns.STATUS;

class Task {

	String name
	String description
	String priority
	String status
	
	static belongsTo = [user: User]
	
    static constraints = {
		name blank:false
		priority blank:false, inList:["BAIXA", "MÉDIA", "ALTA"]
		status blank:false, inList:["FAZER", "FAZENDO", "TESTES", "CONCLUÍDO"]
		description widget: 'textarea'
    }
	

	String toString() {
		return name
    }
}
