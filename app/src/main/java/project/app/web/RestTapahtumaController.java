package project.app.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import project.app.domain.Tapahtuma;
import project.app.domain.TapahtumaRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RestTapahtumaController {

    private static final Logger logger = LoggerFactory.getLogger(RestTapahtumaController.class);

    @Autowired
    private TapahtumaRepository repository;

    // REST haetaan kaikki tapahtumat
    @GetMapping("/tapahtumat")
    public List<Tapahtuma> getAllTapahtumat() {
        logger.info("Fetching all tapahtumat");
        return (List<Tapahtuma>) repository.findAll();
    }

    // REST haetaan tapahtumat id:llä
    @GetMapping("/tapahtumat/{id}")
    public ResponseEntity<Tapahtuma> getTapahtumaById(@PathVariable Long id) {
        logger.info("Fetching tapahtuma with id: {}", id);
        Optional<Tapahtuma> tapahtuma = repository.findById(id);
        return tapahtuma.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // REST luodaan uusi tapahtuma
    @PostMapping("/tapahtumat")
    public Tapahtuma createTapahtuma(@RequestBody Tapahtuma tapahtuma) {
        logger.info("Creating new tapahtuma");
        return repository.save(tapahtuma);
    }

    // REST päivitetään tapahtuma id:llä
    @PatchMapping("/tapahtumat/{id}")
    public ResponseEntity<Tapahtuma> updateTapahtuma(@PathVariable Long id, @RequestBody Tapahtuma updatedTapahtuma) {
        logger.info("Updating tapahtuma with id: {}", id);

        Optional<Tapahtuma> tapahtuma = repository.findById(id);

        if (tapahtuma.isPresent()) {
            Tapahtuma existingTapahtuma = tapahtuma.get();

            if (updatedTapahtuma.getNimi() != null) {
                existingTapahtuma.setNimi(updatedTapahtuma.getNimi());
            }
            if (updatedTapahtuma.getAika() != null) {
                existingTapahtuma.setAika(updatedTapahtuma.getAika());
            }
            if (updatedTapahtuma.getPaikka() != null) {
                existingTapahtuma.setPaikka(updatedTapahtuma.getPaikka());
            }
            if (updatedTapahtuma.getKuvaus() != null) {
                existingTapahtuma.setKuvaus(updatedTapahtuma.getKuvaus());
            }
            if (updatedTapahtuma.getLippumaara() != 0) {
                existingTapahtuma.setLippumaara(updatedTapahtuma.getLippumaara());
            }
            if (updatedTapahtuma.getEnnakkomyynti() != null) {
                existingTapahtuma.setEnnakkomyynti(updatedTapahtuma.getEnnakkomyynti());
            }

            repository.save(existingTapahtuma);
            return ResponseEntity.ok(existingTapahtuma);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // REST poistetaan tapahtuma id:llä
    @DeleteMapping("/tapahtumat/{id}")
    public ResponseEntity<Void> deleteTapahtumaById(@PathVariable Long id) {
        logger.info("Deleting tapahtuma with id: {}", id);
        Optional<Tapahtuma> tapahtuma = repository.findById(id);

        if (tapahtuma.isPresent()) {
            repository.delete(tapahtuma.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
