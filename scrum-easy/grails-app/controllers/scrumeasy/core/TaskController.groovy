package scrumeasy.core

import org.springframework.dao.DataIntegrityViolationException
import org.springframework.util.StopWatch.TaskInfo;

import scrumeasy.core.enuns.PRIORITY;
import scrumeasy.core.enuns.STATUS;
import grails.plugins.springsecurity.Secured;


class TaskController extends BaseController{

	def springSecurityService
    
	static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST']]

    def index() {
        redirect action: 'list', params: params
    }

    def list() {
		
		def user  = springSecurityService.currentUser
		
       /* params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [taskInstanceList: Task.list(params), taskInstanceTotal: Task.count()]*/
		
		log.info "* Method: list"
		log.debug "* Params: ${params}"
		println "* Method: list"
		println "* Params: ${params}"

		params.max = Math.min(params.max ? params.int('max') : 10, 100)

		def results = Task.createCriteria().list(max: params.max, offset: params.offset ?: 0) {
			if (params['filter']) {
				or {
					ilike("name", "%${params.filter}%")
					ilike("status", "%${params.filter}%")
					ilike("priority", "%${params.filter}%")
					ilike("description", "%${params.filter}%")
				}
			}

			eq('user', user)

			maxResults(params.max)

			if (params.order) {
				order(params.sort, params.order)
			}
		}

		if (!isAjax(request)) {
			[taskInstanceList: results, taskInstanceTotal: results.totalCount]
		} else {
			render(template: "list", model: [taskInstanceList: results, taskInstanceTotal: results.totalCount])
		}
		
		
		
    }

    def create() {
		
		def user  = springSecurityService.currentUser
		
		def taskInstance = new Task(params)
		taskInstance.user = user
		switch (request.method) {
		case 'GET':
        	[taskInstance: taskInstance]
			break
		case 'POST':
	        if (!taskInstance.save(flush: true)) {
	            render view: 'create', model: [taskInstance: taskInstance]
	            return
	        }

			flash.message = message(code: 'default.created.message', args: [message(code: 'task.label', default: 'Task'), taskInstance.id])
	        redirect action: 'show', id: taskInstance.id
			break
		}
    }

    def show() {
        def taskInstance = Task.get(params.id)
        if (!taskInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'task.label', default: 'Task'), params.id])
            redirect action: 'list'
            return
        }

        [taskInstance: taskInstance]
    }

    def edit() {
		switch (request.method) {
		case 'GET':
	        def taskInstance = Task.get(params.id)
	        if (!taskInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'task.label', default: 'Task'), params.id])
	            redirect action: 'list'
	            return
	        }

	        [taskInstance: taskInstance]
			break
		case 'POST':
	        def taskInstance = Task.get(params.id)
	        if (!taskInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'task.label', default: 'Task'), params.id])
	            redirect action: 'list'
	            return
	        }

	        if (params.version) {
	            def version = params.version.toLong()
	            if (taskInstance.version > version) {
	                taskInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
	                          [message(code: 'task.label', default: 'Task')] as Object[],
	                          "Another user has updated this Task while you were editing")
	                render view: 'edit', model: [taskInstance: taskInstance]
	                return
	            }
	        }

	        taskInstance.properties = params

	        if (!taskInstance.save(flush: true)) {
	            render view: 'edit', model: [taskInstance: taskInstance]
	            return
	        }

			flash.message = message(code: 'default.updated.message', args: [message(code: 'task.label', default: 'Task'), taskInstance.id])
	        redirect action: 'show', id: taskInstance.id
			break
		}
    }

    def delete() {
		
		println "* Method: delete"
		println "* Params: ${params}"
		
        def taskInstance = Task.get(params.id)
        if (!taskInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'task.label', default: 'Task'), params.id])
            redirect action: 'list'
            return
        }

        try {
            taskInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'task.label', default: 'Task'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'task.label', default: 'Task'), params.id])
            redirect action: 'show', id: params.id
        }
    }
}
