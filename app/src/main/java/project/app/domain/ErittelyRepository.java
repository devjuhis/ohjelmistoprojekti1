package project.app.domain;

import org.springframework.data.repository.CrudRepository;
import java.util.List;


public interface ErittelyRepository extends CrudRepository<Erittely, Long> {

    List<Erittely> findByLippu(Lippu lippu);
}
