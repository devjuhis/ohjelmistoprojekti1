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
public class MaksuTapahtumaRestTest {

    @Autowired
    private WebApplicationContext webAppContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() throws Exception {

        mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
    }

    @Test
    public void statusOk() throws Exception {
        mockMvc.perform(get("/api/maksutapahtumat")).andExpect(status().isOk());
    }

    @Test
    public void responseIsJson() throws Exception {

        mockMvc.perform(get("/api/maksutapahtumat"))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    }

    @Test
    public void testPostMaksutapahtuma() throws Exception {

        String newMaksu = """
                {
                "hintayhteensa": 20,
                "aikaleima": "2024-10-04T11:54:11.701583",
                "kayttaja": {
                    "kayttajaId": 1
                    }
                }
                """;

        mockMvc.perform(post("/api/maksutapahtumat")
        .contentType(MediaType.APPLICATION_JSON)
        .content(newMaksu))
        .andExpect(status().isCreated()) 
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.kayttaja.kayttajaId").value("1"));
    }

    @Test
    public void testSoftDeleteMaksutapahtuma() throws Exception {
        mockMvc.perform(patch("/api/maksutapahtumat/1")) 
                .andExpect(status().isOk()); 
    }
    
}
