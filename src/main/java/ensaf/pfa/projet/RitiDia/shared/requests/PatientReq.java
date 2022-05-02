package ensaf.pfa.projet.RitiDia.shared.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor @NoArgsConstructor

public class PatientReq implements Serializable {
    private String nom;
    private String prenom;
    private String cin;
    private Date date_naissance;
    private boolean est_diabetique;
    private String tel;
    private String sexe;
}
