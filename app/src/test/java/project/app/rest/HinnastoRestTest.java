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
public class HinnastoRestTest {

    

    @Autowired
    private WebApplicationContext webAppContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() throws Exception {

        mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
    }


    @Test
    public void statusOk() throws Exception {
        mockMvc.perform(get("/api/hinnastot")).andExpect(status().isOk());
    }

    @Test
    public void responseIsJson() throws Exception {
        
        mockMvc.perform(get("/api/hinnastot"))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
    }

    @Test
    public void testPostHinnastot() throws Exception {

        String newHinnasto = """
                {
                "tapahtuma": {
                    "tapahtumaId": 1
                        },
                "hintaluokka": "aikuinen", 
                "hinta": 20
                }
                """;

        mockMvc.perform(post("/api/hinnastot")
        .contentType(MediaType.APPLICATION_JSON)
        .content(newHinnasto))
        .andExpect(status().isCreated()) 
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.hintaluokka").value("aikuinen"));
    }

    @Test
    public void testPatchHinnasto() throws Exception {
        String newHinta = """
                {
                    "hinta": 8
                }
                """;

        mockMvc.perform(patch("/api/hinnastot/1") 
                .contentType(MediaType.APPLICATION_JSON)
                .content(newHinta)) 
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.hinta").value(8)); 
    }

    @Test
    public void testDeleteHinnasto() throws Exception {
        mockMvc.perform(delete("/api/hinnastot/2")) 
                .andExpect(status().isNoContent()); 
    }


}
