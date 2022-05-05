package ensaf.pfa.projet.RitiDia.services.implementations;
 //Author : Moncef Tokka

import ensaf.pfa.projet.RitiDia.Repositories.PatientRepository;
import ensaf.pfa.projet.RitiDia.entities.Control;
import ensaf.pfa.projet.RitiDia.entities.Medcin;
import ensaf.pfa.projet.RitiDia.entities.Patient;
import ensaf.pfa.projet.RitiDia.entities.enumerations.Stade;
import ensaf.pfa.projet.RitiDia.services.interfaces.IPatientService;
import ensaf.pfa.projet.RitiDia.shared.dto.ControlDto;
import ensaf.pfa.projet.RitiDia.shared.dto.MedcinDto;
import ensaf.pfa.projet.RitiDia.shared.dto.PatientDetailsDto;
import ensaf.pfa.projet.RitiDia.shared.dto.PatientDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
@Transactional
public class PatientService implements IPatientService {
    @Autowired
    PatientRepository patientRepository;


    @Override
    public PatientDto createPatient(PatientDto patientDto) {
        Patient checkPatient = patientRepository.findByCin(patientDto.getCin());
        if(checkPatient != null) throw new RuntimeException("Le patient est déjà existé!");

        Patient patient = new Patient();
        BeanUtils.copyProperties(patientDto,patient);
        patient.setUuid(UUID.randomUUID().toString());
        System.out.println(patient.getUuid());
        Patient newPatient = patientRepository.save(patient);
        PatientDto returnPatient = new PatientDto();
        BeanUtils.copyProperties(newPatient, returnPatient);

        return returnPatient;
    }
    public Collection<PatientDto> getPatients(){
        List<Patient> patients = patientRepository.findAll();
        patients.forEach(patient -> patient.getControles()
                .forEach(control -> control.setCreated_at(control.getDateControl().getDate_control())));
        patients.forEach(patient ->patient.setControles(patient.getControles()
                .stream().sorted(Comparator.comparing(Control::getCreated_at).reversed()).collect(Collectors.toList())));
        List<PatientDto> patientsDto = new ArrayList<>();
        patients.forEach(patient -> {
            PatientDto patientDto = new PatientDto();
            BeanUtils.copyProperties(patient,patientDto);

            if(!CollectionUtils.isEmpty(patient.getControles())){
                patientDto.setDerniere_consultation(
                        patient.getControles().stream().findFirst().get().getCreated_at()
                );
                if(patient.getControles().stream().findFirst().get().getStadePatient() != null){
                    patientDto.setStade_od(
                            patient.getControles().stream().findFirst().get().getStadePatient().getSod()
                    );
                    patientDto.setStade_og(
                            patient.getControles().stream().findFirst().get().getStadePatient().getSog()
                    );
                }
            }

            patientsDto.add(patientDto);
        });

        return patientsDto;
    }
    public PatientDetailsDto getSinglePatient(Long id) {
        if(patientRepository.findById(id) ==null) throw new RuntimeException("le patient que vous tentez de trouver est introuvable");
        Patient patient = patientRepository.findById(id).get();
        PatientDetailsDto patientDetailsDto = new PatientDetailsDto();
        BeanUtils.copyProperties(patient,patientDetailsDto);
        Collection<ControlDto> custom_controls= new ArrayList<>();
        patient.getControles().forEach(control -> {
            ControlDto controlDto = new ControlDto();
            MedcinDto medcinDto = new MedcinDto();
            if(control.getMedcin() != null){
                BeanUtils.copyProperties(control.getMedcin(),medcinDto);
            }
            controlDto.setMedcin(medcinDto);
            if(control.getStadePatient() != null){
                controlDto.setStade_od(control.getStadePatient().getSod());
                controlDto.setStade_og(control.getStadePatient().getSog());
            }
            controlDto.setId(control.getId());
            custom_controls.add(controlDto);
        });
        patientDetailsDto.setControlsDto(custom_controls);
        //setting age

        LocalDate dob = LocalDate.parse(patient.getDate_naissance().toString());
        LocalDate current_date = LocalDate.now();
        Period period = Period.between(dob,current_date);
        patientDetailsDto.setAge(period.getYears());


        return patientDetailsDto;

    }
}
