package ensaf.pfa.projet.RitiDia.shared.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
@Data @NoArgsConstructor @AllArgsConstructor
public class PatientMinDto  {
    private Long id;
    private String nom;
    private String prenom;
    private Date date_naissance;
    private String cin;
}
