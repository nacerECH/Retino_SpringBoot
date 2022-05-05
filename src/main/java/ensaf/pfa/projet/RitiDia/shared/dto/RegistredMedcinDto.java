package ensaf.pfa.projet.RitiDia.shared.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data @NoArgsConstructor @AllArgsConstructor
public class RegistredMedcinDto implements Serializable {

    private String inpe;
    private String nom;
    private String prenom;
    private String num_tel;
    private String email;
    private String numero_pattente;
    private String password;
    private String Encrypted_password;

}
