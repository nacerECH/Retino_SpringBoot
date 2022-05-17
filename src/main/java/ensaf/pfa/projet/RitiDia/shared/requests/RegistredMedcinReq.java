package ensaf.pfa.projet.RitiDia.shared.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;

@Data @NoArgsConstructor @AllArgsConstructor
public class RegistredMedcinReq implements Serializable {

    private String inpe;
    private String nom;
    private String prenom;
    private String num_tel;
    private String email;
    private String numero_pattente;
    private String password;
}
