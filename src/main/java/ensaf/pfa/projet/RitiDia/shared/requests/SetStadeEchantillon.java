package ensaf.pfa.projet.RitiDia.shared.requests;

import ensaf.pfa.projet.RitiDia.entities.enumerations.Eye;
import ensaf.pfa.projet.RitiDia.entities.enumerations.Stade;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetStadeEchantillon  implements Serializable {


    private Long id;
    private Stade stade;
}
