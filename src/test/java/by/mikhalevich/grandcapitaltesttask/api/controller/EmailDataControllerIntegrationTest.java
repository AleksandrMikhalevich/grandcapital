package by.mikhalevich.grandcapitaltesttask.api.controller;

import by.mikhalevich.grandcapitaltesttask.service.intrfc.EmailDataService;
import by.mikhalevich.grandcapitaltesttask.service.security.jwt.JwtUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Alex Mikhalevich
 * @created 2025-04-28 12:57
 */
@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@Import(EmailDataTestConfig.class)
class EmailDataControllerIntegrationTest {

    static final PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:15.3")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");

    static {
        postgresContainer.start();
    }
    @DynamicPropertySource
    static void overrideDataSourceProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgresContainer::getUsername);
        registry.add("spring.datasource.password", postgresContainer::getPassword);
    }

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmailDataService emailDataService;

    @Test
    void testAddEmailSuccess() throws Exception {
        Long userId = 1L;
        String email = "test@example.com";
        JwtUser jwtUser = new JwtUser(userId, "user", "user");
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user/{userId}/emails", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"" + email + "\"}")
                        .with(user(jwtUser)))
                .andExpect(status().isOk());
        verify(emailDataService, times(1)).addEmailForUser(userId, email);
    }

    @Test
    void testAddEmailForbidden() throws Exception {
        Long pathUserId = 1L;
        Long currentUserId = 2L;
        String email = "test@example.com";
        JwtUser jwtUser = new JwtUser(currentUserId, "user", "user");
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user/{userId}/emails", pathUserId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"" + email + "\"}")
                        .with(user(jwtUser)))
                .andExpect(status().isForbidden());
    }

    @Test
    void testUpdateEmailSuccess() throws Exception {
        Long userId = 1L;
        Long emailDataId = 10L;
        String email = "updated@example.com";
        JwtUser jwtUser = new JwtUser(userId, "user", "user");
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/user/{userId}/emails/{emailDataId}", userId, emailDataId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"" + email + "\"}")
                        .with(user(jwtUser)))
                .andExpect(status().isOk());

        verify(emailDataService, times(1)).updateUserEmail(userId, emailDataId, email);
    }

    @Test
    void testUpdateEmailForbidden() throws Exception {
        Long pathUserId = 1L;
        Long currentUserId = 2L;
        Long emailDataId = 10L;
        String email = "updated@example.com";
        JwtUser jwtUser = new JwtUser(currentUserId, "user", "user");
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/user/{userId}/emails/{emailDataId}", pathUserId, emailDataId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"" + email + "\"}")
                        .with(user(jwtUser)))
                .andExpect(status().isForbidden());
    }

    @Test
    void testDeleteEmailSuccess() throws Exception {
        Long userId = 1L;
        Long emailDataId = 10L;
        JwtUser jwtUser = new JwtUser(userId, "user", "user");
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/user/{userId}/emails/{emailDataId}", userId, emailDataId)
                        .with(user(jwtUser)))
                .andExpect(status().isOk());
        verify(emailDataService, times(1)).deleteUserEmail(userId, emailDataId);
    }

    @Test
    void testDeleteEmailForbidden() throws Exception {
        Long pathUserId = 1L;
        Long currentUserId = 2L;
        Long emailDataId = 10L;
        JwtUser jwtUser = new JwtUser(currentUserId, "user", "user");
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/user/{userId}/emails/{emailDataId}", pathUserId, emailDataId)
                        .with(user(jwtUser)))
                .andExpect(status().isForbidden());
    }
}
