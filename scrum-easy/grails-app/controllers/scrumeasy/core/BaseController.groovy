package scrumeasy.core

class BaseController {



	protected isAjax(request) {
		request.getHeader("X-Requested-With").equals("XMLHttpRequest")
	}

	/**
	 * Add a message object to flash scope, to show messages on
	 * pages, even when a redirect is performed
	 *
	 * @param header
	 * 		Message header
	 * @param message
	 * 		Message text
	 * @param clearPriorNotifications
	 * 		If existing messages should be cleared
	 * @param state
	 * 		Possible values are 'warning', 'error', 'success', 'info'
	 * @param format
	 * 		Possible values are 'basic' and 'block'
	 */


	protected flashNotFoundMessage(id) {
	  flashMessage('default.not.found.message', id)
	}

	protected flashMessage(code, id) {
	  flash.message = message(
	      code: code,
	      args: [label, id]
	  )
	}

	protected ntfy(header = "", message = "", state = "info", format = "block",  clearPriorNotifications = false) {
		// Clear old messages
		if (clearPriorNotifications) {
			clearNotifications()
		}

		def m = new Expando(header: header, message: message, state: state, format: format)

		flash.messages = flash.messages ? flash.messages : []
		flash.messages << m
	}

	protected ntfySuccess(message) {
		ntfy(message, "", "success", "block", true)
	}

	protected ntfyError(message) {
		ntfy(message, "", "error", "block", true)
	}

	protected ntfySaved(args) {
		ntfy("${message(code: 'default.created.message', args: args)}", "", "success", "block", true)
	}

	protected ntfyUpdated(args) {
		ntfy("${message(code: 'default.updated.message', args: args)}", "", "success", "block", true)
	}

	protected ntfyDeleted(args) {
		ntfy("${message(code: 'default.deleted.message', args: args)}", "", "success", "block", true)
	}

	protected ntfyNotFound(args) {
		ntfy("${message(code: 'default.not.found.message', args: args)}", "", "error", "block", true)
	}

	protected ntfyNotDeleted(args) {
		ntfy("${message(code: 'default.not.deleted.message', args: args)}", "", "error", "block", true)
	}

	protected ntfyNotUpdated(args) {
		ntfy("${message(code: 'default.not.updated.message', args: args)}", "", "error", "block", true)
	}

	protected clearNotifications() {
		flash.messages = []
	}

}
