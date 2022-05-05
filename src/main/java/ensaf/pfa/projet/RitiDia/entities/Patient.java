package ensaf.pfa.projet.RitiDia.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Patient {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String uuid;
    private String nom;
    private String prenom;
    @Column(name = "CIN", unique = true)
    private String cin;
    @Temporal(TemporalType.DATE)
    private Date date_naissance;
    private boolean est_diabetique;
    private String tel;
    private String sexe;
    @OneToMany(mappedBy = "patient", fetch = FetchType.EAGER)

    private Collection<Control> controles = new ArrayList<>();

    public Patient(Patient patient) {
        if(patient != null){
            BeanUtils.copyProperties(patient,this);
        }
    }
}
