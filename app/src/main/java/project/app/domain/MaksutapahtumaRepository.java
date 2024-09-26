package project.app.domain;

import org.springframework.data.repository.CrudRepository;
import java.util.List;


public interface MaksutapahtumaRepository extends CrudRepository<Maksutapahtuma, Long> {
    Maksutapahtuma findByMaksutapahtumaId(long maksutapahtumaid);
}
