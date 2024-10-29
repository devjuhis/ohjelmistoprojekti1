package project.app.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import project.app.domain.HinnastoRepository;
import project.app.domain.Hinnasto;
import project.app.domain.Tapahtuma;
import project.app.domain.TapahtumaRepository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api")
public class RestTapahtumaController {

    private static final Logger logger = LoggerFactory.getLogger(RestTapahtumaController.class);

    @Autowired
    private TapahtumaRepository repository;

    @Autowired
    private HinnastoRepository Hrepository;

    // REST haetaan kaikki tapahtumat
    @GetMapping("/tapahtumat")
    public List<Tapahtuma> getAllTapahtumat() {
        logger.info("Fetching all tapahtumat");
        return (List<Tapahtuma>) repository.findAll();
    }

    // REST haetaan kaikki tulevat tapahtumat
    @GetMapping("/tapahtumat/tulevat")
    public List<Tapahtuma> getAllFutureTapahtumat() {
        logger.info("Fetching all future tapahtumat");

        LocalDate currentDate = LocalDate.now();

        return ((List<Tapahtuma>) repository.findAll()).stream()
                .filter(tapahtuma -> tapahtuma.getAika().isAfter(currentDate))
                .collect(Collectors.toList());
    }

    // REST haetaan tapahtumat id:llä
    @GetMapping("/tapahtumat/{id}")
    public ResponseEntity<Tapahtuma> getTapahtumaById(@PathVariable Long id) {
        logger.info("Fetching tapahtuma with id: {}", id);
        Optional<Tapahtuma> tapahtuma = repository.findById(id);

        return tapahtuma.map(ResponseEntity::ok)
                // Vastauskoodi 404, jos tapahtumaa ei löydy id:llä
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // REST haetaan kaikki hinnastot tapahtuma id:llä
    @GetMapping("/tapahtumat/{tapahtumaId}/hinnastot")
    public ResponseEntity<List<Hinnasto>> getHinnastotByTapahtumaId(@PathVariable Long tapahtumaId) {
        logger.info("Fetching liput for tapahtuma with id: {}", tapahtumaId);

        // Hae tapahtuma ID:llä
        Optional<Tapahtuma> tapahtuma = repository.findById(tapahtumaId);

        if (tapahtuma.isPresent()) {
            List<Hinnasto> hinnastot = Hrepository.findByTapahtuma(tapahtuma.get());
            return hinnastot.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(hinnastot);
        } else {
            return ResponseEntity.notFound().build(); // Vastauskoodi 404, jos tapahtumaa ei löydy
        }
    }

    // REST luodaan uusi tapahtuma
    @PostMapping("/tapahtumat")
    public ResponseEntity<?> createTapahtuma(@Valid @RequestBody Tapahtuma tapahtuma, BindingResult bindingResult) {
        logger.info("Creating new tapahtuma");

        // Jos validoinnissa on virheitä...
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.toList());
            // ...validointivirheet listataan vastaukseen:
            return ResponseEntity.badRequest().body(errors);
        } else {
            // Tallennetaan uusi tapahtuma, jos validointi on onnistunut
            // Vastauskoodi 201 Created
            return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(tapahtuma));
        }
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
            // Palautetaan 200 OK, jos tiedot on päivitetty onnistuneesti
            return ResponseEntity.ok(existingTapahtuma);
        } else {
            // Palautetaan 404 Not Found, jos tapahtumaa ei löydy
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
            // Palautetaan 204 No Content, kun tapahtuma on onnistuneesti poistettu
            return ResponseEntity.noContent().build();
        } else {
            // Palautetaan 404 Not Found, jos tapahtumaa ei löydy
            return ResponseEntity.notFound().build();
        }
    }
}
