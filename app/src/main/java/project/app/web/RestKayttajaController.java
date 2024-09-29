package project.app.web;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.app.domain.Kayttaja;
import project.app.domain.KayttajaRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api")
public class RestKayttajaController {

    private static final Logger logger = LoggerFactory.getLogger(RestKayttajaController.class);

    @Autowired
    private KayttajaRepository repository;

    // REST haetaan kaikki käyttäjät
    @GetMapping("/kayttajat")
    public List<Kayttaja> getAllKayttajat() {
        logger.info("Fetching all kayttajat");
        return (List<Kayttaja>) repository.findAll();
    }

    // REST haetaan käyttäjä id:llä
    @GetMapping("/kayttajat/{id}")
    public ResponseEntity<Kayttaja> getKayttajaById(@PathVariable("id") Long id) {
        logger.info("Fetching kayttaja with id: {}", id);
        Optional<Kayttaja> kayttaja = repository.findById(id);

        return kayttaja.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // REST luodaan uusi käyttäjä
    @PostMapping("/kayttajat")
    public Kayttaja createKayttaja(@RequestBody Kayttaja newKayttaja) {
        logger.info("Creating new kayttaja");
        return repository.save(newKayttaja);
    }

    // REST muokataan käyttäjän tietoja
    @PatchMapping("/kayttajat/{id}")
    public ResponseEntity<Kayttaja> editKayttaja(@PathVariable("id") Long id, @RequestBody Kayttaja ediKayttaja) {

        logger.info("Editing kayttaja with id: {}", id);

        Optional<Kayttaja> optionalKayttaja = repository.findById(id);
        if (optionalKayttaja.isPresent()) {
            Kayttaja oldkayttaja = optionalKayttaja.get();

            if (ediKayttaja.getEtunimi() != null) {
                oldkayttaja.setEtunimi(ediKayttaja.getEtunimi());
            }
            if (ediKayttaja.getSukunimi() != null) {
                oldkayttaja.setSukunimi(ediKayttaja.getSukunimi());
            }

            if (ediKayttaja.getSalasana() != null) {
                oldkayttaja.setSalasana(ediKayttaja.getSalasana());
                ;
            }

            if (ediKayttaja.getKayttajatunnus() != null) {
                oldkayttaja.setKayttajatunnus(ediKayttaja.getKayttajatunnus());
            }

            if (ediKayttaja.getOikeus() != null) {
                oldkayttaja.setOikeus(ediKayttaja.getOikeus());
                ;
            }

            repository.save(oldkayttaja);
            return ResponseEntity.ok(oldkayttaja);

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // REST poistetaan käyttäjä
    @DeleteMapping("/kayttajat/{id}")
    public ResponseEntity<Void> deleteKayttajaById(@PathVariable("id") Long id) {
        logger.info("Deleting kayttaja with id: {}", id);

        Optional<Kayttaja> kayttaja = repository.findById(id);

        if (kayttaja.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
