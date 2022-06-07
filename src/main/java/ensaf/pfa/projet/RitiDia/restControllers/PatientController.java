package ensaf.pfa.projet.RitiDia.restControllers;

import ensaf.pfa.projet.RitiDia.Repositories.PatientRepository;
import ensaf.pfa.projet.RitiDia.services.implementations.PatientService;
import ensaf.pfa.projet.RitiDia.shared.dto.PatientDetailsDto;
import ensaf.pfa.projet.RitiDia.shared.dto.PatientDto;
import ensaf.pfa.projet.RitiDia.shared.requests.PatientReq;
import ensaf.pfa.projet.RitiDia.shared.requests.StringRequest;
import ensaf.pfa.projet.RitiDia.shared.responses.PatientRep;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping(value = "/medcins/{medcin_id}/")
public class PatientController {
    @Autowired
    PatientService patientService;
    @Autowired
    PatientRepository patientRepository;


    @PostMapping("/createPatient")
    public ResponseEntity<Object> createPatient(@PathVariable("medcin_id") Long id,@RequestBody PatientReq patientReq){
        PatientDto patientDto = new PatientDto();
        try{
            BeanUtils.copyProperties(patientReq,patientDto);
            PatientDto createdPatient = patientService.createPatient(id,patientDto);
            PatientRep patientRep = new PatientRep();
            BeanUtils.copyProperties(createdPatient, patientRep);
            patientRep.setMessage("Le patient a été bien ajouté!");
            return new ResponseEntity<>(patientRep,HttpStatus.CREATED);
        }catch(RuntimeException re){
            if(re.getMessage() == "Le patient est déjà existé!") {
                return new ResponseEntity<>(re.getMessage(),HttpStatus.IM_USED);
            } else {
                return new ResponseEntity<>(re.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

    }

    @GetMapping("/patients/{id}")
    public ResponseEntity<Object> getSinglePatient(@PathVariable("id") Long id, @PathVariable("medcin_id") Long medcinID){

        try{
            PatientDetailsDto patientRequested = patientService.getSinglePatient(id,medcinID);
            return new ResponseEntity<>(patientRequested,HttpStatus.OK);
        }catch(RuntimeException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }
    @PostMapping("/patients")
    public Object getPatientsByMedcinAndCriteria(@PathVariable("medcin_id") Long id,
                                              @RequestBody StringRequest patternRequest){
        String pattern = patternRequest.getNom_prenom_cin();
        try{
            Collection<PatientDto> patients = patientService.getPatients(id,pattern);
            return new ResponseEntity<>(patients,HttpStatus.OK);
        }catch (RuntimeException e){
            return new ArrayList<String>();
        }

    }



}
