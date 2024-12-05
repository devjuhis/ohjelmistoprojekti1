package project.app.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface MaksutapahtumaRepository extends CrudRepository<Maksutapahtuma, Long> {
    Maksutapahtuma findByMaksutapahtumaId(long maksutapahtumaid);
    List<Maksutapahtuma> findByRemovedFalse();
}
