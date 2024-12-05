package project.app.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import exceptions.*;

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
    public ResponseEntity<?> getLippuById(@PathVariable Long id) {
        logger.info("Fetching lippu with id: {}", id);
        Optional<Lippu> lippu = Lrepository.findById(id);
        
        if (lippu.isPresent()) {
            if (lippu.get().getRemoved()) {
                // Palautetaan 410, jos lippu on poistettu
                CustomErrorResponse errorResponse = new CustomErrorResponse("Kyseinen lippu on poistettu", HttpStatus.GONE.value());
                return ResponseEntity.status(HttpStatus.GONE).body(errorResponse);
            } else {
                return ResponseEntity.ok(lippu.get());
            }
        } else {
            // Palautetaan 404, jos lippua ei löydy
            CustomErrorResponse errorResponse = new CustomErrorResponse("Lippua ei löytynyt annetulla ID:llä", HttpStatus.NOT_FOUND.value());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @GetMapping("/liput/koodi/{koodi}")
    public ResponseEntity<?> getLippuByKoodi(@PathVariable String koodi) {
        logger.info("Fetching lippu with koodi: {}", koodi);

        Optional<Lippu> lippu = Lrepository.findByKoodi(koodi);

        if (lippu.isPresent()) {
            if (lippu.get().getRemoved()) {
                // Palautetaan 410, jos lippu on poistettu
                CustomErrorResponse errorResponse = new CustomErrorResponse("Kyseinen lippu on poistettu", HttpStatus.GONE.value());
                return ResponseEntity.status(HttpStatus.GONE).body(errorResponse);
            } else {
                return ResponseEntity.ok(lippu.get());
            }
        } else {
            // Palautetaan 404, jos lippua ei löydy
            CustomErrorResponse errorResponse = new CustomErrorResponse("Lippua ei löytynyt annetulla koodilla", HttpStatus.NOT_FOUND.value());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }



    // REST haetaan kaikki liput tapahtuma id:llä
    @GetMapping("/tapahtumat/{tapahtumaId}/liput")
    public ResponseEntity<?> getLiputByTapahtumaId(@PathVariable Long tapahtumaId) {
        logger.info("Fetching liput for tapahtuma with id: {}", tapahtumaId);
        
        // Hae tapahtuma ID:llä
        Optional<Tapahtuma> tapahtumaOpt = Trepository.findById(tapahtumaId);
        
        if (!tapahtumaOpt.isPresent()) {
            // Palautetaan 404, jos tapahtumaa ei löydy
            CustomErrorResponse errorResponse = new CustomErrorResponse("Tapahtumaa ei löytynyt ID:llä: " + tapahtumaId, HttpStatus.NOT_FOUND.value());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
        
        Tapahtuma tapahtuma = tapahtumaOpt.get();
        
        // Hae liput tapahtuman mukaan
        List<Lippu> liput = Lrepository.findByTapahtuma(tapahtuma);

        // Suodata pois poistettu liput
        List<Lippu> availableLiput = liput.stream()
                                        .filter(lippu -> !lippu.getRemoved())
                                        .collect(Collectors.toList());
        
        // Palautetaan 404, jos ei löytynyt aktiivisia lippuja
        if (availableLiput.isEmpty()) {
            CustomErrorResponse errorResponse = new CustomErrorResponse("Aktiivisia lippuja ei löytynyt tapahtumalle ID:llä: " + tapahtumaId, HttpStatus.NOT_FOUND.value());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        } else {
            return ResponseEntity.ok(availableLiput);
        }
    }



    // REST haetaan tapahtuman poistetut liput
    @GetMapping("/tapahtumat/{tapahtumaId}/poistetutliput")
    public ResponseEntity<?> getRemovedLiputByTapahtumaId(@PathVariable Long tapahtumaId) {
        logger.info("Fetching poistetut liput for tapahtuma with id: {}", tapahtumaId);
        
        // Hae tapahtuma ID:llä
        Optional<Tapahtuma> tapahtumaOpt = Trepository.findById(tapahtumaId);
        
        if (!tapahtumaOpt.isPresent()) {
            // Palautetaan 404, jos tapahtumaa ei löydy
            CustomErrorResponse errorResponse = new CustomErrorResponse("Tapahtumaa ei löytynyt ID:llä: " + tapahtumaId, HttpStatus.NOT_FOUND.value());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
        
        Tapahtuma tapahtuma = tapahtumaOpt.get();
        
        // Hae liput tapahtuman mukaan
        List<Lippu> liput = Lrepository.findByTapahtuma(tapahtuma);

        // Suodata vain poistettu liput
        List<Lippu> removedLiput = liput.stream()
                                        .filter(Lippu::getRemoved)
                                        .collect(Collectors.toList());
        
        // Palautetaan 404, jos ei löytynyt poistettuja lippuja
        if (removedLiput.isEmpty()) {
            CustomErrorResponse errorResponse = new CustomErrorResponse("Ei löytynyt poistettuja lippuja tapahtumalle ID:llä: " + tapahtumaId, HttpStatus.NOT_FOUND.value());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        } else {
            return ResponseEntity.ok(removedLiput);
        }
    }


    // REST poistetaan lippu id:llä
    @DeleteMapping("/liput/{id}")
    public ResponseEntity<?> deleteLippuById(@PathVariable Long id) {
        logger.info("Deleting lippu with id: {}", id);
        
        // Tarkistetaan, onko lippu olemassa
        if (Lrepository.existsById(id)) {
            try {
                Lrepository.deleteById(id);
                return ResponseEntity.noContent().build();
            } catch (Exception e) {
                logger.error("Error occurred while deleting lippu with id: {}", id, e);
                CustomErrorResponse errorResponse = new CustomErrorResponse("Virhe lippua poistettaessa.", HttpStatus.INTERNAL_SERVER_ERROR.value());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
            }
        } else {
            logger.warn("Lippu not found with id: {}", id);
            CustomErrorResponse errorResponse = new CustomErrorResponse("Lippua ei löytynyt ID:llä: " + id, HttpStatus.NOT_FOUND.value());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }


    // REST soft delete lippu
    @PatchMapping("/liput/softdelete/{id}")
    public ResponseEntity<?> softDeleteLippu(@PathVariable Long id) {
        logger.info("Soft deleting lippu with id: {}", id);
        
        Optional<Lippu> lippu = Lrepository.findById(id);

        if (lippu.isPresent()) {
            if (!lippu.get().getRemoved()) {
                Lippu existingLippu = lippu.get();

                existingLippu.setRemoved(true); // Asetetaan removed-tila true:ksi

                Lrepository.save(existingLippu);
                return ResponseEntity.ok(existingLippu);
            } else {
                // Jos lippu on jo poistettu
                CustomErrorResponse errorResponse = new CustomErrorResponse("Kyseinen lippu on jo poistettu.", HttpStatus.GONE.value());
                return ResponseEntity.status(HttpStatus.GONE).body(errorResponse);
            }
        } else {
            // Jos lippua ei löydy
            CustomErrorResponse errorResponse = new CustomErrorResponse("Lippua ei löytynyt ID:llä: " + id, HttpStatus.NOT_FOUND.value());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }


    // REST päivitetään lippu id:llä
    @PatchMapping("/liput/{id}")
    public ResponseEntity<?> updateLippu(@PathVariable Long id, @RequestBody Lippu updatedLippu) {
        logger.info("Updating lippu with id: {}", id);

        Optional<Lippu> lippu = Lrepository.findById(id);

        if (lippu.isPresent()) {
            Lippu existingLippu = lippu.get();
            CustomErrorResponse errorResponse = null;

            // Validointi
            if (updatedLippu.getKaytetty() != null) {
                if (updatedLippu.getKaytetty() && existingLippu.getRemoved()) {
                    errorResponse = new CustomErrorResponse("Lippua ei voida asettaa käytetyksi, koska se on poistettu.", HttpStatus.BAD_REQUEST.value());
                } else if (updatedLippu.getKaytetty() && existingLippu.getMaara() == -1) {
                    errorResponse = new CustomErrorResponse("Lippua ei voida asettaa käytetyksi, koska se on palautettu.", HttpStatus.BAD_REQUEST.value());
                } else {
                    existingLippu.setKaytetty(updatedLippu.getKaytetty());
                }
            }

            if (updatedLippu.getMaara() != 0) {
                if (updatedLippu.getMaara() == -1 && (existingLippu.getRemoved() || existingLippu.getKaytetty())) {
                    errorResponse = new CustomErrorResponse("Maara ei voi olla -1 (palautettu), jos lippu on poistettu tai käytetty.", HttpStatus.BAD_REQUEST.value());
                } else if (updatedLippu.getMaara() != 1 && updatedLippu.getMaara() != -1) {
                    errorResponse = new CustomErrorResponse("Maara voi olla vain 1 tai -1", HttpStatus.BAD_REQUEST.value());
                } else {
                    existingLippu.setMaara(updatedLippu.getMaara());
                }
            }

            if (updatedLippu.getRemoved() != null) {
                if (updatedLippu.getRemoved() && (existingLippu.getKaytetty() || existingLippu.getMaara() == -1)) {
                    errorResponse = new CustomErrorResponse("Lippua ei voi asettaa 'poistettu', jos se on jo käytetty tai palautettu.", HttpStatus.BAD_REQUEST.value());
                } else {
                    existingLippu.setRemoved(updatedLippu.getRemoved());
                }
            }

            // Tarkista, onko virhettä tapahtunut
            if (errorResponse != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
            }

            Lrepository.save(existingLippu);
            return ResponseEntity.ok(existingLippu);
        } else {
            CustomErrorResponse errorResponse = new CustomErrorResponse("Lippua ei löytynyt ID:llä: " + id, HttpStatus.NOT_FOUND.value());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
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
            // Palautetaan virheviesti CustomErrorResponse-luokan avulla
            CustomErrorResponse errorResponse = new CustomErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    
        } catch (Exception e) {
            logger.error("Unexpected error: {}", e.getMessage());
            // Palautetaan 500 Internal Server Error yleisten virheiden osalta
            CustomErrorResponse errorResponse = new CustomErrorResponse("An unexpected error occurred.", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
    
    // Päivitetään maksutapahtuman hintayhteensä
    private void updateMaksutapahtumanHinta(Long maksutapahtumaId) {
        // Maksutapahtuma ID:n perusteella
        Maksutapahtuma maksutapahtuma = Mrepository.findById(maksutapahtumaId)
                .orElseThrow(() -> new RuntimeException("Maksutapahtuma ei löytynyt"));
    
        // Maksutapahtuman liput
        List<Lippu> liput = Lrepository.findByMaksutapahtuma(maksutapahtuma);
    
        // Kaikkien lippujen hinnat yhteenlaskettuna
        double hintayhteensa = liput.stream()
                .mapToDouble(lippu -> lippu.getHinnasto().getHinta())
                .sum();
    
        // Päivitetään maksutapahtuman hintayhteensä
        maksutapahtuma.setHintayhteensa(hintayhteensa);
    
        // Tallennetaan päivitetty maksutapahtuma
        Mrepository.save(maksutapahtuma);
    }
    
}
