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
public class TapahtumaRestTest {

    @Autowired
    private WebApplicationContext webAppContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() throws Exception {

        mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
    }

    @Test
    public void statusOk() throws Exception {
        mockMvc.perform(get("/api/tapahtumat")).andExpect(status().isOk());
    }

    @Test
    public void responseIsJson() throws Exception {

        mockMvc.perform(get("/api/tapahtumat"))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    }

    @Test
    public void testPostTapahtuma() throws Exception {

        String newTapahtuma = """
                {
                    "nimi": "Postman Tour 2025",
                    "aika": "2025-08-02",
                    "paikka": "Ratinan stadion",
                    "kuvaus": "Vuoden odotetuin",
                    "lippumaara": 1000,
                    "ennakkomyynti": "2025-08-01"
                }
                """;

        mockMvc.perform(post("/api/tapahtumat")
        .contentType(MediaType.APPLICATION_JSON)
        .content(newTapahtuma))
        .andExpect(status().isCreated()) 
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.paikka").value("Ratinan stadion"));
    }

    @Test
    public void testPatchTapahtuma() throws Exception {
        String newPaikka = """
                {
                    "paikka": "Sillan alla"
                }
                """;

        mockMvc.perform(patch("/api/tapahtumat/1") 
                .contentType(MediaType.APPLICATION_JSON)
                .content(newPaikka)) 
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.paikka").value("Sillan alla")); 
    }

    @Test
    public void testDeleteTapahtuma() throws Exception {
        mockMvc.perform(delete("/api/tapahtumat/2")) 
                .andExpect(status().isNoContent()); 
    }
    
}
