package nl.rabobank.org_users_rest

import nl.rabobank.org_users_rest.entity.Role
import org.hamcrest.Matchers.containsString
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import kotlin.test.Test

@SpringBootTest
@AutoConfigureMockMvc
class RolesControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `when request roles return all roles list`() {
        val expectedRolesList: List<Role> = listOf(Role(0, "User"), Role(1, "Moderator"), Role(2, "Admin"))

        mockMvc.perform(get("/api/roles"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Hello, World")));
    }
}