package ensaf.pfa.projet.RitiDia.shared.dto;

import ensaf.pfa.projet.RitiDia.entities.enumerations.Stade;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@Data @NoArgsConstructor @AllArgsConstructor
public class PatientDto implements Serializable {
    private Long id;
    private String nom;
    private String prenom;
    private String cin;
    private Date date_naissance;
    private boolean est_diabetique;
    private String tel;
    private String sexe;
    private Stade stade_od;
    private Stade stade_og;
    private Long medcinID;
    private Date derniere_consultation;

}
