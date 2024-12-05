package project.app.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api") // Base path for the controller
public class AppController {

    private static final Logger logger = LoggerFactory.getLogger(AppController.class);

    // Autowire Springin Environment tai @Value käytettäväksi profiilin saamiseen
    @Value("${spring.profiles.active}")
    private String activeProfile;

    // Constructor to test logger
    public AppController() {
        logger.info("AppController initialized");
    }

    // Endpoint, joka toteaa, että sovellus toimii
    @GetMapping("/test")
    public String checkApplicationStatus() {
        // Logaa aktiivi-profiili
        logger.info("Active profile: " + activeProfile);
        
        // Palautetaan yksinkertainen viesti, joka kertoo sovelluksen toiminnasta
        return "Application is running. Active profile: " + activeProfile;
    }
}

