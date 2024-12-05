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
public class TapahtumaTests {

    @Autowired
    TapahtumaRepository trepository;

    // Testataan tapahtuman haku id:llä
    @Test
    public void TestFindById() {
        LocalDate aika = LocalDate.of(2024, 12, 14);
        Tapahtuma tapahtuma = new Tapahtuma("testitapahtuma", aika, "jossain", "kuvaus", 30, aika);
        trepository.save(tapahtuma);

        Long tapahtumaId = tapahtuma.getTapahtumaId();
        Optional<Tapahtuma> fetchedTapahtuma = trepository.findById(tapahtumaId);
        assertThat(fetchedTapahtuma).isPresent();
    }

    // Testataan Tapahtuman poisto
    @Test
    public void TestDeleteTapahtuma() {
        LocalDate aika = LocalDate.of(2024, 12, 14);
        Tapahtuma tapahtuma = new Tapahtuma("testitapahtuma", aika, "jossain", "kuvaus", 30, aika);
        trepository.save(tapahtuma);

        Long tapahtumaId = tapahtuma.getTapahtumaId();
        trepository.deleteById(tapahtumaId);
        Optional<Tapahtuma> deletedTapahtuma = trepository.findById(tapahtumaId);

        assertThat(deletedTapahtuma).isNotPresent();

    }

    // Testataan tapahtuman muokkaaminen
    @Test
    public void TestUpdateTapahtuma() {

        LocalDate aika = LocalDate.of(2024, 12, 14);
        Tapahtuma tapahtuma = new Tapahtuma("testitapahtuma", aika, "jossain", "kuvaus", 30, aika);
        trepository.save(tapahtuma);

        Long tapahtumaId = tapahtuma.getTapahtumaId();
        Optional<Tapahtuma> fetchedTapahtuma = trepository.findById(tapahtumaId);

        assertThat(fetchedTapahtuma).isPresent();
        Tapahtuma updatedTapahtuma = fetchedTapahtuma.get();
        updatedTapahtuma.setPaikka("Uusi paikka");
        updatedTapahtuma.setKuvaus("Uusi kuvaus");
        trepository.save(updatedTapahtuma);
        Optional<Tapahtuma> updatedFetchedTapahtuma = trepository.findById(tapahtumaId);

        assertThat(updatedFetchedTapahtuma).isPresent();
        assertThat(updatedFetchedTapahtuma.get().getPaikka()).isEqualTo("Uusi paikka");
        assertThat(updatedFetchedTapahtuma.get().getKuvaus()).isEqualTo("Uusi kuvaus");

    }

}
