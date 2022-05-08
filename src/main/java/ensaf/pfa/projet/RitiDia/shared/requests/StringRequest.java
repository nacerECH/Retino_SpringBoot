package ensaf.pfa.projet.RitiDia.shared.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data @NoArgsConstructor @AllArgsConstructor
public class StringRequest implements Serializable {
    private String nom_prenom_cin;
}
