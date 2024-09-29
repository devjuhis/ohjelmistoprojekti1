package project.app;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import project.app.domain.Erittely;
import project.app.domain.ErittelyRepository;
import project.app.domain.Hinnasto;
import project.app.domain.HinnastoRepository;
import project.app.domain.Kayttaja;
import project.app.domain.KayttajaRepository;
import project.app.domain.Lippu;
import project.app.domain.LippuRepository;
import project.app.domain.Maksutapahtuma;
import project.app.domain.MaksutapahtumaRepository;
import project.app.domain.Tapahtuma;
import project.app.domain.TapahtumaRepository;

@SpringBootApplication
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@Bean
	public CommandLineRunner ticketGuruDemo(ErittelyRepository erittelyrepo, HinnastoRepository hintarepo,
			KayttajaRepository kayttajarepo, LippuRepository lippurepo, MaksutapahtumaRepository maksurepo,
			TapahtumaRepository tapahtumarepo) {
		return (args) -> {

			LocalDate aika = LocalDate.of(2024, 12, 14);
			LocalDate myynninloppu = LocalDate.of(2024, 12, 13);
			LocalDateTime aikaleima = LocalDateTime.now();

			tapahtumarepo.save(
					new Tapahtuma("Uusi tapahtuma", aika, "Olympiastadion", "Hyvä tapahtuma :D", 600, myynninloppu));
			hintarepo.save(new Hinnasto(tapahtumarepo.findByTapahtumaId(1), "opiskelija", 12));
			hintarepo.save(new Hinnasto(tapahtumarepo.findByTapahtumaId(1), "elakelainen", 11));
			kayttajarepo.save(new Kayttaja("matti", "esimerkki", "salasana", "matti123", "ADMIN"));
			lippurepo.save(new Lippu(tapahtumarepo.findByTapahtumaId(1), hintarepo.findByHinnastoId(1), 0, 1));

			// erittelyrepo.save(new Erittely(1, null, lippurepo.findByLippuid(1)));
			maksurepo.save(new Maksutapahtuma(55, aikaleima, kayttajarepo.findByKayttajatunnus("matti123")));
			lippurepo.save(new Lippu(tapahtumarepo.findByTapahtumaId(1), hintarepo.findByHinnastoId(1),
					maksurepo.findByMaksutapahtumaId(1), 0, 1));
			lippurepo.save(new Lippu(tapahtumarepo.findByTapahtumaId(1), hintarepo.findByHinnastoId(1),
					maksurepo.findByMaksutapahtumaId(1), 0, 1));

			Lippu muutettavaLippu = lippurepo.findByLippuId(1);
			muutettavaLippu.setMaksutapahtuma(maksurepo.findByMaksutapahtumaId(1));
			lippurepo.save(muutettavaLippu);
		};
	}

}
