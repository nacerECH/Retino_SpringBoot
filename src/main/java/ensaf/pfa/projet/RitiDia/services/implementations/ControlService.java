package ensaf.pfa.projet.RitiDia.services.implementations;

import ensaf.pfa.projet.RitiDia.Repositories.AquisitionRepository;
import ensaf.pfa.projet.RitiDia.Repositories.ControlRepository;
import ensaf.pfa.projet.RitiDia.Repositories.MedcinRepository;
import ensaf.pfa.projet.RitiDia.Repositories.PatientRepository;
import ensaf.pfa.projet.RitiDia.entities.*;
import ensaf.pfa.projet.RitiDia.entities.enumerations.Eye;
import ensaf.pfa.projet.RitiDia.services.interfaces.IControlService;
import ensaf.pfa.projet.RitiDia.shared.dto.*;
import ensaf.pfa.projet.RitiDia.shared.requests.ControlRequest;
import ensaf.pfa.projet.RitiDia.shared.requests.ControlWithBilanReq;
import ensaf.pfa.projet.RitiDia.shared.responses.ControlWithBilanRep;
import ensaf.pfa.projet.RitiDia.shared.responses.MedcinResponse;
import ensaf.pfa.projet.RitiDia.shared.responses.PatientRep;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class ControlService implements IControlService {
    @Autowired
    ControlRepository controlRepository;
    @Autowired
    AquisitionRepository aquisitionRepository;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    MedcinRepository medcinRepository;

    @Override
    public HalfControlDetailsDto getHalfControl(Long id) {
        if(controlRepository.findById(id) == null) throw new RuntimeException("Control n'est pas existant!");
        Control control = controlRepository.findById(id).get();
        HalfControlDetailsDto custom_control = new HalfControlDetailsDto();

        custom_control.setId(control.getId());
        PatientMinDto custom_patient = new PatientMinDto();
        MedcinDto cst_medcin = new MedcinDto();
        if(control.getPatient() !=null){
            BeanUtils.copyProperties(control.getPatient(),custom_patient);
            custom_control.setPatient(custom_patient);
        }
        if(control.getMedcin() !=null){
            BeanUtils.copyProperties(control.getMedcin(),cst_medcin);
            custom_control.setMedcin(cst_medcin);
        }
        Collection<Aquisition> right_aquisitions = aquisitionRepository.findByControle_IdAndType_oeil(id, Eye.RIGHT);
        Collection<Aquisition> left_aquisitions = aquisitionRepository.findByControle_IdAndType_oeil(id, Eye.LEFT);

        Collection<AquisitionDto> right_aqui_dto =new ArrayList<>();
        Collection<AquisitionDto> left_aqui_dto =new ArrayList<>();

        right_aquisitions.forEach(aquisition -> {
            AquisitionDto aquisitionDto = new AquisitionDto();
            BeanUtils.copyProperties(aquisition,aquisitionDto);
            right_aqui_dto.add(aquisitionDto);
        });

        left_aquisitions.forEach(aquisition -> {
            AquisitionDto aquisitionDto = new AquisitionDto();
            BeanUtils.copyProperties(aquisition,aquisitionDto);
            left_aqui_dto.add(aquisitionDto);
        });

        custom_control.setRight_aquisitions(right_aqui_dto);
        custom_control.setLeft_aquisition(left_aqui_dto);
        if(control.getStadePatient() != null){
            StadePatientDto stadePatientDto = new StadePatientDto();
            BeanUtils.copyProperties(control.getStadePatient(),stadePatientDto);
            custom_control.setPatient_stade_obj(stadePatientDto);
        }



        return custom_control;
    }

    @Override
    public ControlDetailsDto getSingleControl(Long id) {
        if(controlRepository.findById(id) == null) throw new RuntimeException("Control n'est pas existant!");
        Control control = controlRepository.findById(id).get();
        ControlDetailsDto custom_control = new ControlDetailsDto();

        custom_control.setId(control.getId());
        PatientMinDto custom_patient = new PatientMinDto();
        MedcinDto cst_medcin = new MedcinDto();
        if(control.getPatient() !=null){
            BeanUtils.copyProperties(control.getPatient(),custom_patient);
            custom_control.setPatient(custom_patient);
        }
        if(control.getMedcin() !=null){
            BeanUtils.copyProperties(control.getMedcin(),cst_medcin);
            custom_control.setMedcin(cst_medcin);
        }
        Collection<Aquisition> right_aquisitions = aquisitionRepository.findByControle_IdAndType_oeil(id, Eye.RIGHT);
        Collection<Aquisition> left_aquisitions = aquisitionRepository.findByControle_IdAndType_oeil(id, Eye.LEFT);

        Collection<AquisitionDto> right_aqui_dto =new ArrayList<>();
        Collection<AquisitionDto> left_aqui_dto =new ArrayList<>();

        right_aquisitions.forEach(aquisition -> {
            AquisitionDto aquisitionDto = new AquisitionDto();
            BeanUtils.copyProperties(aquisition,aquisitionDto);
            right_aqui_dto.add(aquisitionDto);
        });

        left_aquisitions.forEach(aquisition -> {
            AquisitionDto aquisitionDto = new AquisitionDto();
            BeanUtils.copyProperties(aquisition,aquisitionDto);
            left_aqui_dto.add(aquisitionDto);
        });

        custom_control.setRight_aquisitions(right_aqui_dto);
        custom_control.setLeft_aquisition(left_aqui_dto);
        if(control.getStadePatient() != null){
            StadePatientDto stadePatientDto = new StadePatientDto();
            BeanUtils.copyProperties(control.getStadePatient(),stadePatientDto);
            custom_control.setPatient_stade_obj(stadePatientDto);
        }
        custom_control.setBilan(control.getBilan());
        custom_control.setMedcin_2(control.getMedcin_2());

        control.setCreated_at(control.getDateControl().getDate_control());
        custom_control.setCreated_at(control.getCreated_at());

        return custom_control;
    }

    @Override
    public ControlWithBilanRep updateControl(Long id, ControlWithBilanReq new_control) {
        if(controlRepository.findById(id) ==null)
            throw new RuntimeException("Vous tentez de modifier un control qui est de base inexistant");
        Control updated_control = controlRepository.findById(id).get();
        updated_control.setBilan(new_control.getBilan());
        updated_control.setMedcin_2(new_control.getMedcin_2());
        Control control = controlRepository.save(updated_control);
        ControlWithBilanRep new_ctrl = new ControlWithBilanRep();
        BeanUtils.copyProperties(control,new_ctrl);
        return new_ctrl;
    }

    @Override
    public Collection<Control> getAllControls() {
        Collection<Control> controls = controlRepository.findAll();
        return controls;
    }

    @Override
    public ControlResponseDto createControl(ControlRequest controlRequest) {

        Optional<Patient> patient = patientRepository.findById(controlRequest.getPatientID());
        patient.orElseThrow(()-> new RuntimeException("patient non trouvé"));
        Optional<Medcin> medcin = medcinRepository.findById(controlRequest.getMedcinID());
        medcin.orElseThrow(()-> new RuntimeException("medcin non trouvé"));

        Control control = new Control();
        control.setPatient(patient.map(Patient::new).get());
        control.setMedcin(medcin.map(Medcin::new).get());
        DateControl dateControl = new DateControl();
        dateControl.setDate_control(new Date());
        control.setDateControl(dateControl);
        control.setCreated_at(control.getDateControl().getDate_control());

        Control savedControl = controlRepository.save(control);
        ControlResponseDto controlResponseDto = new ControlResponseDto();
        PatientRep patientRep = new PatientRep();
        MedcinResponse medcinResponse = new MedcinResponse();

        BeanUtils.copyProperties(control.getPatient(),patientRep);
        BeanUtils.copyProperties(control.getMedcin(),medcinResponse);

        controlResponseDto.setPatient(patientRep);
        controlResponseDto.setMedcin(medcinResponse);
        controlResponseDto.setCreated_at(control.getCreated_at());
        controlResponseDto.setControlID(control.getId());


        return controlResponseDto;
    }
}
