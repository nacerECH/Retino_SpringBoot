package ensaf.pfa.projet.RitiDia.shared.responses;


import ensaf.pfa.projet.RitiDia.entities.enumerations.Eye;
import ensaf.pfa.projet.RitiDia.shared.dto.MedcinDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class EchantillonResponce implements Serializable {

    private Long id;
    private Date create_at;
    private Eye eye ;

}
