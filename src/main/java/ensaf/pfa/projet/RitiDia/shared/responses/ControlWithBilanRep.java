package ensaf.pfa.projet.RitiDia.shared.responses;

import ensaf.pfa.projet.RitiDia.shared.dto.MedcinDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ControlWithBilanRep {
    private Long id;
    private String medcin_2;
    private String bilan;
}
