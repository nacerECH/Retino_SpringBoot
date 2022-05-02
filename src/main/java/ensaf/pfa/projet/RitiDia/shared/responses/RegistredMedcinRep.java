package ensaf.pfa.projet.RitiDia.shared.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class RegistredMedcinRep {
    private String inpe;
    private String nom;
    private String prenom;
    private String num_tel;
    private String email;
    private String numero_pattente;

}
