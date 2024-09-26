package project.app.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api") // Base path for the controller
public class AppController {

    private static final Logger logger = LoggerFactory.getLogger(AppController.class);

    // Constructor to test logger
    public AppController() {
        logger.info("AppController initialized");
    }
}

