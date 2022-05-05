package ensaf.pfa.projet.RitiDia.shared.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data @NoArgsConstructor @AllArgsConstructor
public class MedcinDto implements Serializable {

    private String nom;
    private String prenom;
}
