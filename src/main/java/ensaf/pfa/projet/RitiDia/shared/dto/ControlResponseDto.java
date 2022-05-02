package ensaf.pfa.projet.RitiDia.shared.dto;

import ensaf.pfa.projet.RitiDia.shared.responses.MedcinResponse;
import ensaf.pfa.projet.RitiDia.shared.responses.PatientRep;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data @NoArgsConstructor @AllArgsConstructor
public class ControlResponseDto implements Serializable {
    private Long controlID;
    private Date created_at;
    private PatientRep patient;
    private MedcinResponse medcin;

}
