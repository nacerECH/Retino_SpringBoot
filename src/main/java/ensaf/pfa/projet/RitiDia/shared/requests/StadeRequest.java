package ensaf.pfa.projet.RitiDia.shared.requests;

import ensaf.pfa.projet.RitiDia.entities.enumerations.Stade;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class StadeRequest {

    private String sog;
    private String sod;
}
