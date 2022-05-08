package ensaf.pfa.projet.RitiDia.shared.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageDto  implements Serializable {

    private Long id;
    private String Url;

}
