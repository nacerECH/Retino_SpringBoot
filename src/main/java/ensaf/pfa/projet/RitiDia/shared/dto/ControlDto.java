package ensaf.pfa.projet.RitiDia.shared.dto;

import ensaf.pfa.projet.RitiDia.entities.enumerations.Stade;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data @NoArgsConstructor @AllArgsConstructor
public class ControlDto implements Serializable {
    private Long id;
    private MedcinDto medcin;
    private Stade stade_og;
    private Stade stade_od;
    private Date created_at;
}
