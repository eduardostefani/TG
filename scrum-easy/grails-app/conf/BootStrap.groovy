import scrumeasy.core.auth.Role;
import scrumeasy.core.auth.User;
import scrumeasy.core.auth.UserRole;

class BootStrap {

    def springSecurityService
	
	def init = { servletContext ->

		
		def userRole = Role.findByAuthority('ROLE_USER') ?: new Role(authority: 'ROLE_USER').save(failOnError: true)
		def adminRole = Role.findByAuthority('ROLE_ADMIN') ?: new Role(authority: 'ROLE_ADMIN').save(failOnError: true)

		def adminUser = User.findByUsername('admin') ?: new User(
				username: 'admin',
				password: 'admin',
				enabled: true).save(failOnError: true)

		if (!adminUser.authorities.contains(adminRole)) {
			try{
				UserRole.create(adminUser, userRole)
				UserRole.create(adminUser, adminRole)
			}catch(Exception e){
				System.out.println(e.getMessage());
				//log.erro "Could not to insert role for user ${e.message}"
			}
		}
	}
	
	def destroy = {
	}
}
