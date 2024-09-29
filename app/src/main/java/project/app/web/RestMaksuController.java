package project.app.web;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.app.domain.Lippu;
import project.app.domain.LippuRepository;
import project.app.domain.Maksutapahtuma;
import project.app.domain.MaksutapahtumaRepository;

@RestController
@RequestMapping("/api")
public class RestMaksuController {

    private static final Logger logger = LoggerFactory.getLogger(RestMaksuController.class);

    @Autowired
    private MaksutapahtumaRepository maksurepository;

    @Autowired
    private LippuRepository lippurepository;

    // REST haetaan kaikki maksutapahtumat
    @GetMapping("/maksutapahtumat")
    public List<Maksutapahtuma> getAllTapahtumat() {
        logger.info("Fetching all maksutapahtumat");
        return (List<Maksutapahtuma>) maksurepository.findAll();
    }

    // REST haetaan maksutapahtuma id:llä
    @GetMapping("/maksutapahtumat/{id}")
    public ResponseEntity<Maksutapahtuma> getTapahtumaById(@PathVariable Long id) {
        logger.info("Fetching maksutapahtuma with id: {}", id);
        Optional<Maksutapahtuma> mtapahtuma = maksurepository.findById(id);
        return mtapahtuma.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // REST haetaan kaikki liput yhdelle maksutapahtumaid:lle
    @GetMapping("/maksutapahtumat/{id}/liput")
    public ResponseEntity<List<Lippu>> getLiputByTapahtumaId(@PathVariable Long id) {
        logger.info("Fetching liput for maksutapahtuma with id: {}", id);

        // Haetaan maksutapahtumat ID:llä
        Optional<Maksutapahtuma> maksutapahtuma = maksurepository.findById(id);

        if (maksutapahtuma.isPresent()) {
            List<Lippu> liput = lippurepository.findByMaksutapahtuma(maksutapahtuma.get()); 
            return liput.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(liput);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}