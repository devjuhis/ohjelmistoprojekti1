package project.app.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;


public interface LippuRepository extends CrudRepository<Lippu, Long> {

    Lippu findByLippuId(long lippuid);
    List<Lippu> findByTapahtuma(Tapahtuma tapahtuma);
    List<Lippu> findByMaksutapahtuma(Maksutapahtuma maksutapahtuma);
    List<Lippu> findByRemovedFalse();
    Optional<Lippu> findByKoodi(String koodi);
}
