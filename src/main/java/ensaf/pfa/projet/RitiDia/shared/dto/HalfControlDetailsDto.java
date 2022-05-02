package ensaf.pfa.projet.RitiDia.shared.dto;

import ensaf.pfa.projet.RitiDia.entities.enumerations.Stade;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collection;
@Data  @NoArgsConstructor @AllArgsConstructor
public class HalfControlDetailsDto implements Serializable {
    private Long id;
    private PatientMinDto patient;
    private MedcinDto medcin;
    private Collection<AquisitionDto> right_aquisitions;
    private Collection<AquisitionDto> left_aquisition;
    private StadePatientDto patient_stade_obj;
}
