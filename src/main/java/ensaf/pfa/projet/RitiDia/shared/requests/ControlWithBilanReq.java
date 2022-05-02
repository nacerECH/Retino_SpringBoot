package ensaf.pfa.projet.RitiDia.shared.requests;

import ensaf.pfa.projet.RitiDia.shared.dto.MedcinDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class ControlWithBilanReq {
    private Long id;
    private String medcin_2;
    private String bilan;
}
