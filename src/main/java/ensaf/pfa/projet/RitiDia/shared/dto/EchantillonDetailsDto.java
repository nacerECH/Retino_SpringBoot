package ensaf.pfa.projet.RitiDia.shared.dto;

import ensaf.pfa.projet.RitiDia.entities.enumerations.Eye;
import ensaf.pfa.projet.RitiDia.entities.enumerations.Stade;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class EchantillonDetailsDto implements Serializable {

    private Long id;
    private MedcinDto medcin;
    private Date create_at;
    private Eye eye ;
    private Stade stade;
    private Collection<ImageDto> imageDtos ;

}
