package ensaf.pfa.projet.RitiDia.shared.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data @NoArgsConstructor @AllArgsConstructor
public class PatientRep  {
    private String nom;
    private String prenom;
    private boolean est_diabetique;
    private String sexe;
    private String message;

}
