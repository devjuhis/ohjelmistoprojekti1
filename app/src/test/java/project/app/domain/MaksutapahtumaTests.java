package project.app.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("h2")
public class MaksutapahtumaTests {

    @Autowired
    private MaksutapahtumaRepository mrepository;

    @Autowired
    private KayttajaRepository krepository;

    // Testataan Maksutapahtumien haku paitsi poistetut
    @Test
    public void TestGetAll() {
        Kayttaja kayttaja = new Kayttaja("erkki", "esimerkki", "salanana", "esmes1", "USER");
        krepository.save(kayttaja);
        Long kayttajaId = kayttaja.getKayttajaId();
        Kayttaja fetchedKayttaja = krepository.findById(kayttajaId)
                .orElseThrow(() -> new RuntimeException("Käyttäjää ei löydy"));
        Maksutapahtuma maksutapahtuma1 = new Maksutapahtuma();
        maksutapahtuma1.setKayttaja(fetchedKayttaja);
        LocalDateTime aikaleima = LocalDateTime.now();
        maksutapahtuma1.setAikaleima(aikaleima);
        mrepository.save(maksutapahtuma1);

        Maksutapahtuma maksutapahtuma2 = new Maksutapahtuma();
        maksutapahtuma2.setKayttaja(fetchedKayttaja);
        maksutapahtuma2.setAikaleima(aikaleima);
        maksutapahtuma2.setRemoved(true);
        mrepository.save(maksutapahtuma2);

        List<Maksutapahtuma> result = mrepository.findByRemovedFalse();

        assertThat(result).hasSize(2); // testidata + testi
        assertThat(result.get(1).getRemoved()).isFalse();
    }

    // Testataan maksutapahtuman luonti
    @Test
    public void TestCreateNew() {
        Kayttaja kayttaja = new Kayttaja("erkki", "esimerkki", "salanana", "esmes2", "USER");
        krepository.save(kayttaja);
        Long kayttajaId = kayttaja.getKayttajaId();
        Kayttaja fetchedKayttaja = krepository.findById(kayttajaId)
                .orElseThrow(() -> new RuntimeException("Käyttäjää ei löydy"));
        Maksutapahtuma maksutapahtuma = new Maksutapahtuma();
        maksutapahtuma.setKayttaja(fetchedKayttaja);
        LocalDateTime aikaleima = LocalDateTime.now();
        maksutapahtuma.setAikaleima(aikaleima);
        mrepository.save(maksutapahtuma);
        assertThat(maksutapahtuma.getMaksutapahtumaId()).isNotNull();
        assertThat(maksutapahtuma.getKayttaja()).isEqualTo(fetchedKayttaja);
    }

    // Testataan maksutapahtuman soft delete
    @Test
    public void TestSoftDelete() {
        Kayttaja kayttaja = new Kayttaja("esimerkki", "erkki", "salanana", "esmes3", "USER");
        krepository.save(kayttaja);
        Long kayttajaId = kayttaja.getKayttajaId();
        Kayttaja fetchedKayttaja = krepository.findById(kayttajaId)
                .orElseThrow(() -> new RuntimeException("Käyttäjää ei löydy"));
        Maksutapahtuma maksutapahtuma = new Maksutapahtuma();
        maksutapahtuma.setKayttaja(fetchedKayttaja);
        LocalDateTime aikaleima = LocalDateTime.now();
        maksutapahtuma.setAikaleima(aikaleima);
        maksutapahtuma.setRemoved(true);
        mrepository.save(maksutapahtuma);

        Maksutapahtuma fetchedMaksutapahtuma = mrepository.findById(maksutapahtuma.getMaksutapahtumaId())
                .orElseThrow(() -> new RuntimeException("Maksutapahtumaa ei löydy"));

        assertThat(fetchedMaksutapahtuma.getRemoved()).isTrue();
    }
}
