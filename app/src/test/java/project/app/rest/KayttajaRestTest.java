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
public class KayttajaRestTest {

    @Autowired
    private WebApplicationContext webAppContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() throws Exception {

        mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
    }

    @Test
    public void statusOk() throws Exception {
        mockMvc.perform(get("/api/kayttajat")).andExpect(status().isOk());
    }

    @Test
    public void responseIsJson() throws Exception {

        mockMvc.perform(get("/api/kayttajat"))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    }

    @Test
    public void testPostKayttajat() throws Exception {

        String newKayttaja = """
                {
                    "etunimi": "minna",
                    "sukunimi": "esimerkki",
                    "salasana": "salasana",
                    "kayttajatunnus": "minna123",
                    "oikeus": "USER"
                }
                """;

        mockMvc.perform(post("/api/kayttajat")
        .contentType(MediaType.APPLICATION_JSON)
        .content(newKayttaja))
        .andExpect(status().isCreated()) 
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.kayttajatunnus").value("minna123"));
    }

    @Test
    public void testPatchKayttaja() throws Exception {
        String newKayttaja = """
                {
                    "kayttajatunnus": "testi123"
                }
                """;

        mockMvc.perform(patch("/api/kayttajat/1") 
                .contentType(MediaType.APPLICATION_JSON)
                .content(newKayttaja)) 
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.kayttajatunnus").value("testi123")); 
    }

    @Test
    public void testDeleteKayttaja() throws Exception {
        mockMvc.perform(delete("/api/kayttajat/2")) 
                .andExpect(status().isNoContent()); 
    }

    @Test
    public void testSoftDeleteKayttaja() throws Exception {
        mockMvc.perform(patch("/api/kayttajat/softdelete/1")) 
                .andExpect(status().isOk()); 
    }
    
}
