package project.app.web;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import exceptions.CustomErrorResponse;
import jakarta.validation.Valid;
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
    private PasswordEncoder passwordEncoder;

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
    public ResponseEntity<?> createKayttaja(@Valid @RequestBody Kayttaja newKayttaja) {
    logger.info("Creating new kayttaja");

    // Testataan onko käyttäjätunnus olemassa
    Optional<Kayttaja> existingKayttaja = Optional.ofNullable(repository.findByKayttajatunnus(newKayttaja.getKayttajatunnus()));
    
    if (existingKayttaja.isPresent()) {
        // Jos käyttäjätunnus on olemassa palautetaan koodi 400
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new CustomErrorResponse("Error: Username already exists", HttpStatus.BAD_REQUEST.value()));
    }
    
    // Tallennetaan käyttäjä ja kryptataan salasana, jos käyttäjätunnus on uniikki
    Kayttaja savedKayttaja = newKayttaja;
    savedKayttaja.setSalasana(passwordEncoder.encode(newKayttaja.getSalasana()));
    repository.save(savedKayttaja);
    
    return ResponseEntity.status(HttpStatus.CREATED).body(savedKayttaja);
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
                oldkayttaja.setSalasana(passwordEncoder.encode(ediKayttaja.getSalasana()));
            }

            if (ediKayttaja.getKayttajatunnus() != null) {
                oldkayttaja.setKayttajatunnus(ediKayttaja.getKayttajatunnus());
            }

            if (ediKayttaja.getOikeus() != null) {
                oldkayttaja.setOikeus(ediKayttaja.getOikeus());
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

    // REST soft delete käyttäjälle

    @PatchMapping("/kayttajat/softdelete/{id}")
    public ResponseEntity<Kayttaja> softDeleteMaksutapahtuma(@PathVariable Long id) {
        logger.info("Soft deleting kayttaja with id: {}", id);

        // Haetaan maksutapahtumat ID:llä
        Optional<Kayttaja> kayttaja = repository.findById(id);

        if (kayttaja.isPresent()) {
            // haetaan aktiivinen käyttäjä
            if(kayttaja.get().getAktiivisuus()) {
                Kayttaja kayttaja2 = kayttaja.get();
                // asetetaan käyttäjä epäaktiiviseksi
                kayttaja2.setAktiivisuus(false);

                repository.save(kayttaja2);
                return ResponseEntity.ok(kayttaja2);
            } else {
                return ResponseEntity.status(HttpStatus.GONE).build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
