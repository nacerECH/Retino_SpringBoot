package ensaf.pfa.projet.RitiDia.webSecurity.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class AuthenticationRequest {
    private String username;
    private String password;
}

