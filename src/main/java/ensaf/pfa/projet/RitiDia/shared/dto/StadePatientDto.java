package ensaf.pfa.projet.RitiDia.shared.dto;

import ensaf.pfa.projet.RitiDia.entities.enumerations.Stade;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class StadePatientDto {
    private Long id ;
    private Stade sod;
    private Stade sog;
}
