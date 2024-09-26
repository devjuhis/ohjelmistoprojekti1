package project.app.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import project.app.domain.Lippu;
import project.app.domain.LippuRepository;

import project.app.domain.Tapahtuma;
import project.app.domain.TapahtumaRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RestLippuController {

    private static final Logger logger = LoggerFactory.getLogger(RestTapahtumaController.class);

    @Autowired
    private LippuRepository Lrepository; 

    @Autowired
    private TapahtumaRepository Trepository; 

    // REST haetaan kaikki liput
    @GetMapping("/liput")
    public List<Lippu> getAllLippus() {	
        logger.info("Fetching all lippus");
        return (List<Lippu>) Lrepository.findAll();
    }

    // REST haetaan liput id:llä
    @GetMapping("/liput/{id}")
    public ResponseEntity<Lippu> getLippuById(@PathVariable Long id) {
        logger.info("Fetching lippu with id: {}", id);
        Optional<Lippu> lippu = Lrepository.findById(id);
        return lippu.map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // REST haetaan kaikki liput tapahtuma id:llä
    @GetMapping("/tapahtumat/{tapahtumaId}/liput")
    public ResponseEntity<List<Lippu>> getLiputByTapahtumaId(@PathVariable Long tapahtumaId) {
        logger.info("Fetching liput for tapahtuma with id: {}", tapahtumaId);
        
        // Hae tapahtuma ID:llä
        Optional<Tapahtuma> tapahtuma = Trepository.findById(tapahtumaId);
        
        if (tapahtuma.isPresent()) {
            List<Lippu> liput = Lrepository.findByTapahtuma(tapahtuma.get()); // Hae liput tapahtumalle
            return liput.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(liput);
        } else {
            return ResponseEntity.notFound().build(); // Palautetaan 404, jos tapahtumaa ei löydy
        }
    }

    // REST poistetaan lippu id:llä
    @DeleteMapping("/liput/{id}")
    public ResponseEntity<Void> deleteLippuById(@PathVariable Long id) {
        logger.info("Deleting lippu with id: {}", id);
        Optional<Lippu> lippu = Lrepository.findById(id);

        if (lippu.isPresent()) {
            Lrepository.delete(lippu.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // REST luodaan uusi lippu
    @PostMapping("/liput")
    public Lippu createLippu(@RequestBody Lippu lippu) {
        logger.info("Creating new lippu");
        return Lrepository.save(lippu);
    }
}
