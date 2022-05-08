package ensaf.pfa.projet.RitiDia.entities;


import ensaf.pfa.projet.RitiDia.entities.enumerations.Eye;
import ensaf.pfa.projet.RitiDia.entities.enumerations.Stade;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Echantillon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Eye eye;
    @Enumerated(EnumType.STRING)
    private Stade stade;
    @Temporal(TemporalType.DATE)
    private Date date_acquisition;


    @ManyToOne
    private Medcin medcin;

    @OneToMany(mappedBy = "echantillon", fetch = FetchType.EAGER)
    private Collection<Image> images;

}
