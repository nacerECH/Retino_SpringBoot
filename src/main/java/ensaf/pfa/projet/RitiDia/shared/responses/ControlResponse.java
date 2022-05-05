package ensaf.pfa.projet.RitiDia.shared.responses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data @NoArgsConstructor @AllArgsConstructor
public class ControlResponse implements Serializable {
    private Long controlID;
    private Date created_at;
    private PatientRep patient;
    private MedcinResponse medcin;
}
