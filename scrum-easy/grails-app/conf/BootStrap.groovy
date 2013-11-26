import scrumeasy.core.auth.Role;
import scrumeasy.core.auth.User;
import scrumeasy.core.auth.UserRole;

class BootStrap {

    def springSecurityService
	
	def init = { servletContext ->

		
		def userRole = Role.findByAuthority('ROLE_USER') ?: new Role(authority: 'ROLE_USER').save(failOnError: true)
		def adminRole = Role.findByAuthority('ROLE_ADMIN') ?: new Role(authority: 'ROLE_ADMIN').save(failOnError: true)
		def addTaskRole = Role.findByAuthority('ROLE_TASK_ADD') ?: new Role(authority: 'ROLE_TASK_ADD').save(failOnError: true)
		def editTaskRole = Role.findByAuthority('ROLE_TASK_EDIT') ?: new Role(authority: 'ROLE_TASK_EDIT').save(failOnError: true)
		def removeTaskRole = Role.findByAuthority('ROLE_TASK_DEL') ?: new Role(authority: 'ROLE_TASK_DEL').save(failOnError: true)

		def adminUser = User.findByUsername('Eduardo') ?: new User(
				username: 'Eduardo',
				password: 'admin',
				enabled: true).save(failOnError: true)

		if (!adminUser.authorities.contains(adminRole)) {
			try{
				UserRole.create(adminUser, userRole)
				UserRole.create(adminUser, adminRole)
				UserRole.create(adminUser, addTaskRole)
				UserRole.create(adminUser, editTaskRole)
				UserRole.create(adminUser, removeTaskRole)
			}catch(Exception e){
				log.erro "Could not to insert role for user ${e.message}"
			}
		}
	}
	
	def destroy = {
	}
}
