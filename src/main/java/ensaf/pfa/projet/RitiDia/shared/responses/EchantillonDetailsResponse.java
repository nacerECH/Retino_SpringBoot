package ensaf.pfa.projet.RitiDia.shared.responses;

import ensaf.pfa.projet.RitiDia.entities.Image;
import ensaf.pfa.projet.RitiDia.entities.Medcin;
import ensaf.pfa.projet.RitiDia.entities.enumerations.Eye;
import ensaf.pfa.projet.RitiDia.entities.enumerations.Stade;
import ensaf.pfa.projet.RitiDia.shared.dto.ImageDto;
import ensaf.pfa.projet.RitiDia.shared.dto.MedcinDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EchantillonDetailsResponse  implements Serializable {
    private Long id;

    //private Long medcinID;
    //private Medcin medcin;
   // private Date create_at;
   private Date date_acquisition;
    private Eye eye ;
    private Stade stade;
    private Collection<ImageDto> imagesDto ;

}
