package ensaf.pfa.projet.RitiDia.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor

public class Control {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(mappedBy = "controle", fetch = FetchType.EAGER,cascade=CascadeType.ALL)

    private DateControl dateControl;
    @OneToMany(mappedBy = "controle", fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    private Collection<Aquisition> aquisitions;
    @OneToOne(mappedBy = "controle", fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    private StadePatient stadePatient;
    @ManyToOne
    private Medcin medcin;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Patient patient;
    @Transient
    private Date created_at;

    private String medcin_2;
    private String bilan;
}
