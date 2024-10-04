package project.app.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import project.app.domain.Lippu;
import project.app.domain.LippuRepository;
import project.app.domain.Maksutapahtuma;
import project.app.domain.MaksutapahtumaRepository;
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

    @Autowired
    private MaksutapahtumaRepository Mrepository;

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
            List<Lippu> liput = Lrepository.findByTapahtuma(tapahtuma.get());
            return liput.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(liput);
        } else {
            return ResponseEntity.notFound().build(); // Palautetaan 404, jos tapahtumaa ei löydy
        }
    }

    // REST poistetaan lippu id:llä
    @DeleteMapping("/liput/{id}")
    public ResponseEntity<Void> deleteLippuById(@PathVariable Long id) {
        logger.info("Deleting lippu with id: {}", id);
        if (Lrepository.existsById(id)) {
            Lrepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // REST luodaan uusi lippu
    @PostMapping("/liput")
    public Lippu createLippu(@RequestBody Lippu lippu) {
        logger.info("Creating new lippu");
        // Tallennetaan uusi lippu
        Lippu savedLippu = Lrepository.save(lippu);

        // Laske hintayhteensä maksutapahtumalle
        updateMaksutapahtumanHinta(savedLippu.getMaksutapahtuma().getMaksutapahtumaId());

    return savedLippu;

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


    // REST päivitetään tapahtuma id:llä
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
            if (updatedLippu.getKaytetty() != 0) {
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
}
