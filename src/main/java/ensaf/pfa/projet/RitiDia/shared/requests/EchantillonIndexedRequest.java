package ensaf.pfa.projet.RitiDia.shared.requests;

import ensaf.pfa.projet.RitiDia.entities.enumerations.Eye;
import ensaf.pfa.projet.RitiDia.entities.enumerations.Stade;
import ensaf.pfa.projet.RitiDia.shared.dto.ImageDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collection;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class EchantillonIndexedRequest implements Serializable {


    private Long medcinID;
    private Eye eye ;
    private Stade stade;
    private Collection<ImageDto> imageDtos ;

}
