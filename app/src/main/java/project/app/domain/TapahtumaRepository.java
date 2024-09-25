package project.app.domain;

import org.springframework.data.repository.CrudRepository;

public interface TapahtumaRepository extends CrudRepository<Tapahtuma, Long> {

    Tapahtuma findByTapahtumaid(long tapahtumaid);
}
