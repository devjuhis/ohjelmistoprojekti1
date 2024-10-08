package project.app.web;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.app.domain.Hinnasto;
import project.app.domain.HinnastoRepository;

@RestController
@RequestMapping("/api")
public class RestHinnastoController {

    private static final Logger logger = LoggerFactory.getLogger(RestHinnastoController.class);

    @Autowired
    private HinnastoRepository hinnastoRepository;

    // REST haetaan kaikki hinnastot
    @GetMapping("/hinnastot")
    public List<Hinnasto> getAllHinnastot() {
        logger.info("Fetching all hinnastot");
        return (List<Hinnasto>) hinnastoRepository.findAll();
    }

    // REST haetaan hinnasto id:llä
    @GetMapping("/hinnastot/{id}")
    public ResponseEntity<Hinnasto> getHinnastoById(@PathVariable("id") Long id) {
        logger.info("Fetching hinnasto with id: {}", id);
        Optional<Hinnasto> hinnasto = hinnastoRepository.findById(id);

        return hinnasto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // REST luodaan uusi hinnasto
    @PostMapping("/hinnastot")
    public Hinnasto createHinnasto(@RequestBody Hinnasto newHinnasto) {
        logger.info("Creating new hinnasto");
        return hinnastoRepository.save(newHinnasto);
    }

    // REST muokataan hinnaston tietoja
    @PatchMapping("/hinnastot/{id}")
    public ResponseEntity<Hinnasto> editHinnasto(@PathVariable("id") Long id, @RequestBody Hinnasto ediHinnasto) {

        logger.info("Editing Hinnasto with id: {}", id);

        Optional<Hinnasto> optionalHinnasto = hinnastoRepository.findById(id);
        if (optionalHinnasto.isPresent()) {
            Hinnasto oldHinnasto = optionalHinnasto.get();

            if (ediHinnasto.getTapahtuma() != null) {
                oldHinnasto.setTapahtuma((ediHinnasto.getTapahtuma()));
            }
            if (ediHinnasto.getHintaluokka() != null) {
                oldHinnasto.setHintaluokka((ediHinnasto.getHintaluokka()));
            }
            if (ediHinnasto.getHinta() != 0) {
                oldHinnasto.setHinta(ediHinnasto.getHinta());
            }
            
            hinnastoRepository.save(oldHinnasto);
            return ResponseEntity.ok(oldHinnasto);

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // REST poistetaan hinnasto
    @DeleteMapping("/hinnastot/{id}")
    public ResponseEntity<Void> deleteHinnastoById(@PathVariable("id") Long id) {
        logger.info("Deleting Hinnasto with id: {}", id);

        if (hinnastoRepository.existsById(id)) {
            hinnastoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
}
