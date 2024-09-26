package project.app.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.app.domain.Maksutapahtuma;
import project.app.domain.MaksutapahtumaRepository;

@RestController
@RequestMapping("/api")
public class RestMaksuController {

     private static final Logger logger = LoggerFactory.getLogger(RestMaksuController.class);

    @Autowired
    private MaksutapahtumaRepository repository; 

    // REST haetaan kaikki tapahtumat
    @GetMapping("/maksutapahtumat")
    public List<Maksutapahtuma> getAllTapahtumat() {	
        logger.info("Fetching all maksutapahtumat");
        return (List<Maksutapahtuma>) repository.findAll();
    }
}
