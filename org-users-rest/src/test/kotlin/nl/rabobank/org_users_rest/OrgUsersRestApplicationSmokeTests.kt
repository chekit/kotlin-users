package nl.rabobank.org_users_rest

import nl.rabobank.org_users_rest.controller.RolesController
import nl.rabobank.org_users_rest.controller.UsersController
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class OrgUsersRestApplicationSmokeTests {

    @Autowired
    private lateinit var usersController: UsersController

    @Autowired
    private lateinit var rolesController: RolesController

    @Test
    fun contextLoads() {
        assertThat(usersController).isNotNull()
        assertThat(rolesController).isNotNull()
    }
}
