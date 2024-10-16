package project.app.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import project.app.domain.Hinnasto;
import project.app.domain.HinnastoRepository;
import project.app.domain.Lippu;
import project.app.domain.LippuRepository;
import project.app.domain.Maksutapahtuma;
import project.app.domain.MaksutapahtumaRepository;
import project.app.domain.Tapahtuma;
import project.app.domain.TapahtumaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class RestLippuController {

    private static final Logger logger = LoggerFactory.getLogger(RestTapahtumaController.class);

    @Autowired
    private LippuRepository Lrepository; 

    @Autowired
    private TapahtumaRepository Trepository; 

    @Autowired
    private MaksutapahtumaRepository Mrepository;

    @Autowired
    private HinnastoRepository Hrepository;

    // REST haetaan kaikki liput
    @GetMapping("/liput")
    public List<Lippu> getLiput() {	
        logger.info("Fetching all liput excluding removed");
        return Lrepository.findByRemovedFalse(); // Hakee kaikki liput, joissa removed on false
    }

    // REST haetaan liput id:llä
    @GetMapping("/liput/{id}")
    public ResponseEntity<Lippu> getLippuById(@PathVariable Long id) {
        logger.info("Fetching lippu with id: {}", id);
        Optional<Lippu> lippu = Lrepository.findById(id);
        
        if (lippu.isPresent()) {
            if (lippu.get().getRemoved()) {
                
                return ResponseEntity.status(HttpStatus.GONE).build(); // Palautetaan 410, jos lippu on poistettu
            } else {
                return ResponseEntity.ok(lippu.get());
            }
        } else {
            return ResponseEntity.notFound().build(); // Palautetaan 404, jos lippua ei löydy
        }
    }

    // REST haetaan kaikki liput tapahtuma id:llä
    @GetMapping("/tapahtumat/{tapahtumaId}/liput")
    public ResponseEntity<List<Lippu>> getLiputByTapahtumaId(@PathVariable Long tapahtumaId) {
        logger.info("Fetching liput for tapahtuma with id: {}", tapahtumaId);
        
        // Hae tapahtuma ID:llä ja heitä RuntimeException, jos ei löydy
        Tapahtuma tapahtuma = Trepository.findById(tapahtumaId)
            .orElseThrow(() -> new RuntimeException("Tapahtumaa ei löydy ID:llä: " + tapahtumaId));
        
        // Hae liput tapahtuman mukaan
        List<Lippu> liput = Lrepository.findByTapahtuma(tapahtuma);

        // Suodata pois poistettu liput
        List<Lippu> availableLiput = liput.stream()
                                        .filter(lippu -> !lippu.getRemoved())
                                        .collect(Collectors.toList());
        
        // Palautetaan 404, jos ei löytynyt aktiivisia lippuja
        return availableLiput.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(availableLiput);
    }

    // REST haetaan tapahtuman poistetut liput
    @GetMapping("/tapahtumat/{tapahtumaId}/poistetutliput")
    public ResponseEntity<List<Lippu>> getRemovedLiputByTapahtumaId(@PathVariable Long tapahtumaId) {
        logger.info("Fetching liput for tapahtuma with id: {}", tapahtumaId);
        
        // Hae tapahtuma ID:llä ja heitä RuntimeException, jos ei löydy
        Tapahtuma tapahtuma = Trepository.findById(tapahtumaId)
            .orElseThrow(() -> new RuntimeException("Tapahtumaa ei löydy ID:llä: " + tapahtumaId));
        
        // Hae liput tapahtuman mukaan
        List<Lippu> liput = Lrepository.findByTapahtuma(tapahtuma);

        // Suodata pois poistettu liput
        List<Lippu> availableLiput = liput.stream()
                                        .filter(lippu -> lippu.getRemoved())
                                        .collect(Collectors.toList());
        
        // Palautetaan 404, jos ei löytynyt aktiivisia lippuja
        return availableLiput.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(availableLiput);
    }

    // REST poistetaan lippu id:llä
    @DeleteMapping("/liput/{id}")
    public ResponseEntity<Void> deleteLippuById(@PathVariable Long id) {
        logger.info("Deleting lippu with id: {}", id);
        try {
            if (Lrepository.existsById(id)) {
                Lrepository.deleteById(id);
                return ResponseEntity.noContent().build();
            } else {
                logger.warn("Lippu not found with id: {}", id);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.error("Error occurred while deleting lippu with id: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // REST soft delete lippu
    @PatchMapping("/liput/softdelete/{id}")
    public ResponseEntity<Lippu> softDeleteLippu(@PathVariable Long id) {
        logger.info("Soft deleting lippu with id: {}", id);

        Optional<Lippu> lippu = Lrepository.findById(id);

        if (lippu.isPresent()) {
            if (!lippu.get().getRemoved()) {
                Lippu existingLippu = lippu.get();

                // Muokataan vain removed-tilaa
                existingLippu.setRemoved(true); // Asetetaan removed-tila true:ksi

                Lrepository.save(existingLippu);
                return ResponseEntity.ok(existingLippu);
            } else {
                return ResponseEntity.status(HttpStatus.GONE).build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // REST päivitetään lippu id:llä
    @PatchMapping("/liput/{id}")
    public ResponseEntity<Lippu> updateLippu(@PathVariable Long id, @RequestBody Lippu updatedLippu) {
        logger.info("Updating lippu with id: {}", id);

        Optional<Lippu> lippu = Lrepository.findById(id);

        if (lippu.isPresent()) {
            Lippu existingLippu = lippu.get();

            //voidaan poistaa mahdollisuus muokata tapahtumaa, hinnastoa ja maksutapahtumaa REST API:ssa 
            //poistamalla ne tästä...

            if (updatedLippu.getTapahtuma() != null) {
                existingLippu.setTapahtuma(updatedLippu.getTapahtuma());
            }
            if (updatedLippu.getHinnasto() != null) {
                existingLippu.setHinnasto(updatedLippu.getHinnasto());
            }
            if (updatedLippu.getMaksutapahtuma() != null) {
                existingLippu.setMaksutapahtuma(updatedLippu.getMaksutapahtuma());
            }
            if (updatedLippu.getKaytetty() != null) {
                existingLippu.setKaytetty(updatedLippu.getKaytetty());
            }
            if (updatedLippu.getMaara() != 0) {
                existingLippu.setMaara(updatedLippu.getMaara());
            }

            Lrepository.save(existingLippu);
            return ResponseEntity.ok(existingLippu);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    // REST luodaan uusi lippu
    @PostMapping("/liput")
    public ResponseEntity<?> createLippu(@RequestBody Lippu lippu) {
        logger.info("Creating new lippu");

        try {
            
            Tapahtuma tapahtuma = Trepository.findById(lippu.getTapahtuma().getTapahtumaId())
                    .orElseThrow(() -> new RuntimeException("Tapahtumaa ei löydy id: " + lippu.getTapahtuma().getTapahtumaId()));
            lippu.setTapahtuma(tapahtuma);

            Hinnasto hinnasto = Hrepository.findById(lippu.getHinnasto().getHinnastoid())
                    .orElseThrow(() -> new RuntimeException("Hinnastoa ei löytynyt id: " + lippu.getHinnasto().getHinnastoid()));
            lippu.setHinnasto(hinnasto);

            Maksutapahtuma maksutapahtuma = Mrepository.findById(lippu.getMaksutapahtuma().getMaksutapahtumaId())
                    .orElseThrow(() -> new RuntimeException("Maksutapahtumaa ei löytynyt id: " + lippu.getMaksutapahtuma().getMaksutapahtumaId()));
            lippu.setMaksutapahtuma(maksutapahtuma);

            Lippu savedLippu = Lrepository.save(lippu);

            updateMaksutapahtumanHinta(savedLippu.getMaksutapahtuma().getMaksutapahtumaId());

            // Palautetaan 201 Created ja tallennettu lippu
            return ResponseEntity.status(HttpStatus.CREATED).body(savedLippu);

        } catch (RuntimeException e) {
            logger.error("Error creating lippu: {}", e.getMessage());
            // Palautetaan 400 Bad Request ja virheviesti
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        } catch (Exception e) {
            logger.error("Unexpected error: {}", e.getMessage());
            // Palautetaan 500 Internal Server Error yleisten virheiden osalta
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
        }
    }

 
    // Päivitetään maksutapahtuman hintayhteensä
    private void updateMaksutapahtumanHinta(Long maksutapahtumaId) {
        // Maksutapahtuma ID:n perusteella
        Maksutapahtuma maksutapahtuma = Mrepository.findById(maksutapahtumaId).orElseThrow(() -> new RuntimeException("Maksutapahtuma ei löytynyt"));

        // Maksutapahtuman liput
        List<Lippu> liput = Lrepository.findByMaksutapahtuma(maksutapahtuma);

        // kaikkien lippujen hinnat yhteenlaskettuna
        double hintayhteensa = liput.stream()
                                    .mapToDouble(lippu -> lippu.getHinnasto().getHinta())
                                    .sum();

        // Päivitetään maksutapahtuman hintayhteensä
        maksutapahtuma.setHintayhteensa(hintayhteensa);

        // Tallennetaan päivitetty maksutapahtuma
        Mrepository.save(maksutapahtuma);
    }
}
