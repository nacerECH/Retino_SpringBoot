package ensaf.pfa.projet.RitiDia.shared.dto;

import ensaf.pfa.projet.RitiDia.entities.enumerations.Stade;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Data @NoArgsConstructor @AllArgsConstructor
public class PatientDetailsDto implements Serializable {

    private String nom;
    private String prenom;
    private String cin;
    private int age;
    private boolean est_diabetique;
    private String sexe;
    private Collection<ControlDto> controlsDto;
}
