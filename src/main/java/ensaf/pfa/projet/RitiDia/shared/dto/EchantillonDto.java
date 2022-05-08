package ensaf.pfa.projet.RitiDia.shared.dto;


import ensaf.pfa.projet.RitiDia.entities.enumerations.Eye;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EchantillonDto implements Serializable {

    private Long id;
    private MedcinDto medcin;
    private Date create_at;
    private Eye eye ;

}
