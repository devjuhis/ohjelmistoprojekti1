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
public class LippuRestTest {

    @Autowired
    private WebApplicationContext webAppContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() throws Exception {

        mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
    }

    @Test
    public void statusOk() throws Exception {
        mockMvc.perform(get("/api/liput")).andExpect(status().isOk());
    }

    @Test
    public void responseIsJson() throws Exception {
        
        mockMvc.perform(get("/api/liput"))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    }


    @Test
    public void testPostLippu() throws Exception {

        String newLippu = """
                {
                "tapahtuma": {
                        "tapahtumaId": 1
                        },
                "hinnasto": {
                        "hinnastoid": 1
                        },
                "maksutapahtuma": {
                        "maksutapahtumaId": 1
                        }
                }
                """;

        mockMvc.perform(post("/api/liput")
        .contentType(MediaType.APPLICATION_JSON)
        .content(newLippu))
        .andExpect(status().isCreated()) 
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.tapahtuma.tapahtumaId").value(1));
    }

    @Test
    public void testPatchLippu() throws Exception {
        String newLippu = """
                {
                    "maara": -1
                }
                """;

        mockMvc.perform(patch("/api/liput/1") 
                .contentType(MediaType.APPLICATION_JSON)
                .content(newLippu)) 
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.maara").value(-1)); 
    }

    @Test
    public void testDeleteLippu() throws Exception {
        mockMvc.perform(delete("/api/liput/2")) 
                .andExpect(status().isNoContent()); 
    }

    @Test
    public void testSoftDeleteLippu() throws Exception {
        mockMvc.perform(patch("/api/liput/softdelete/3")) 
                .andExpect(status().isOk()); 
    }
    
}
