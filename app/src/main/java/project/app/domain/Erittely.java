package project.app.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Erittely {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long erittely_id;
    private Integer maara;
//FK maksutapahtuma_id 
    @ManyToOne
    @JoinColumn(name = "maksutapahtuma_id")
    private Maksutapahtuma maksutapahtuma;
//FK lippu_id 
    @ManyToOne
    @JoinColumn(name = "lippu_id")
    private Lippu lippu;

    
  


}
