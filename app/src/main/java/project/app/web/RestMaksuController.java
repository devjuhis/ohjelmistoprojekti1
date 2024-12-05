package project.app.web;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import exceptions.CustomErrorResponse;
import project.app.domain.Kayttaja;
import project.app.domain.KayttajaRepository;
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

    @Autowired
    private KayttajaRepository kayttajarepository;

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

    // REST luodaan uusi maksutapahtuma
    @PostMapping("/maksutapahtumat")
    public ResponseEntity<?> createMaksutapahtuma(@RequestBody Maksutapahtuma maksutapahtuma) {
        logger.info("Creating new maksutapahtuma");

        try {
            Kayttaja kayttaja = kayttajarepository.findById(maksutapahtuma.getKayttaja().getKayttajaId())
                    .orElseThrow(() -> new RuntimeException("Käyttäjää ei löydy"));
            maksutapahtuma.setKayttaja(kayttaja);
            LocalDateTime aikaleima = LocalDateTime.now();
            maksutapahtuma.setAikaleima(aikaleima);
            return ResponseEntity.status(HttpStatus.CREATED).body(maksurepository.save(maksutapahtuma));
        } catch (RuntimeException e) {
            logger.error("Error creating maksutapahtuma: {}", e.getMessage());
            // Palautetaan 400 Bad Request ja virheviesti
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new CustomErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value()));

        } catch (Exception e) {
            logger.error("Unexpected error: {}", e.getMessage());
            // Palautetaan 500 Internal Server Error yleisten virheiden osalta
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CustomErrorResponse("Unexpected error", HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
    }

    // REST soft delete maksutapahtuma
    @PatchMapping("/maksutapahtumat/{id}")
    public ResponseEntity<Maksutapahtuma> softDeleteMaksutapahtuma(@PathVariable Long id) {
        logger.info("Soft deleting maksutapahtuma with id: {}", id);

        // Haetaan maksutapahtumat ID:llä
        Optional<Maksutapahtuma> maksutapahtuma = maksurepository.findById(id);

        if (maksutapahtuma.isPresent()) {
            // haetaan jos maksutapahtumaa ei ole merkitty poistetuksi
            if (!maksutapahtuma.get().getRemoved()) {
                Maksutapahtuma maksutapahtumaOk = maksutapahtuma.get();
                // asetetaan maksutapahtuma poistetuksi
                maksutapahtumaOk.setRemoved(true);
                maksurepository.save(maksutapahtumaOk);
                return ResponseEntity.ok(maksutapahtumaOk);
            } else {
                return ResponseEntity.status(HttpStatus.GONE).build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
