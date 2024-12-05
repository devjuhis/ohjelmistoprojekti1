package project.app.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@ActiveProfiles("h2")
public class LoginRestTest {

    @Autowired
    private WebApplicationContext webAppContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() throws Exception {

        mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
    }

    @Test
    public void testLogin() throws Exception {

        String login = """
            {
	            "kayttajatunnus":"matti123",
	            "salasana": "salasana"
            }
            """;

            mockMvc.perform(post("/api/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(login))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.jwt").exists());
    }

    @Test
    public void testInvalidLogin() throws Exception {

        String wrongLogin = """
                {
                    "username": "vaaranimi",
                    "password": "vaarasalasana"
                }
                """;

        mockMvc.perform(post("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(wrongLogin))
                .andExpect(status().isUnauthorized()) 
                .andExpect(jsonPath("$.message").value("Virheelliset tunnukset"));
    }   

    
}
