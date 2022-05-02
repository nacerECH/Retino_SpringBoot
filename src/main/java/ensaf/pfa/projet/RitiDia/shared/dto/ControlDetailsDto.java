package ensaf.pfa.projet.RitiDia.shared.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Data @NoArgsConstructor @AllArgsConstructor
public class ControlDetailsDto implements Serializable {
    private Long id;
    private PatientMinDto patient;
    private MedcinDto medcin;
    private String medcin_2;
    private Collection<AquisitionDto> right_aquisitions;
    private Collection<AquisitionDto> left_aquisition;
    private StadePatientDto patient_stade_obj;
    private String bilan;
    private Date created_at;
}
