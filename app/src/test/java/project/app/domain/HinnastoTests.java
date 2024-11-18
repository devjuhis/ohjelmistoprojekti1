package project.app.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("h2")
public class HinnastoTests {

    @Autowired
    HinnastoRepository hrepository;
    @Autowired
    TapahtumaRepository trepository;

    // Testataan hinnaston hakeminen id:llä
    @Test
    public void TestFindById() {
        LocalDate aika = LocalDate.of(2024, 12, 14);
        Tapahtuma tapahtuma = new Tapahtuma("tapahtuma", aika, "paikka", "kuvaus", 300, aika);
        trepository.save(tapahtuma);
        Hinnasto hinnasto = new Hinnasto(tapahtuma, "vip", 50.00);
        hrepository.save(hinnasto);

        Long hinnastoId = hinnasto.getHinnastoid();
        Hinnasto fetchedHinnasto = hrepository.findById(hinnastoId)
                .orElseThrow(() -> new RuntimeException("Hinnasto ei löydy"));
        assertThat(fetchedHinnasto.getTapahtuma().getTapahtumaId()).isEqualTo(tapahtuma.getTapahtumaId());
    }

    // Testataan uuden hinnaston luonti
    @Test
    public void TestCreateNew() {
        LocalDate aika = LocalDate.of(2024, 12, 14);
        Tapahtuma tapahtuma = new Tapahtuma("tapahtuma", aika, "paikka", "kuvaus", 300, aika);
        trepository.save(tapahtuma);
        Hinnasto hinnasto = new Hinnasto(tapahtuma, "vip", 50.00);
        hrepository.save(hinnasto);
        assertThat(hinnasto.getHinnastoid()).isNotNull();
    }

    // Testataan Hinnaston poisto
    @Test
    public void TestDeleteHinnasto() {
        LocalDate aika = LocalDate.of(2024, 12, 14);
        Tapahtuma tapahtuma = new Tapahtuma("tapahtuma", aika, "paikka", "kuvaus", 300, aika);
        trepository.save(tapahtuma);
        Hinnasto hinnasto = new Hinnasto(tapahtuma, "vip", 50.00);
        hrepository.save(hinnasto);

        Long hinnastoId = hinnasto.getHinnastoid();
        hrepository.deleteById(hinnastoId);
        Optional<Hinnasto> deletedHinnasto = hrepository.findById(hinnastoId);

        assertThat(deletedHinnasto).isNotPresent();

    }
}
