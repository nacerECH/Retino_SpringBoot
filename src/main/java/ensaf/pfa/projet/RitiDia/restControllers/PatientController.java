package ensaf.pfa.projet.RitiDia.restControllers;

import ensaf.pfa.projet.RitiDia.Repositories.PatientRepository;
import ensaf.pfa.projet.RitiDia.services.implementations.PatientService;
import ensaf.pfa.projet.RitiDia.shared.dto.PatientDto;
import ensaf.pfa.projet.RitiDia.shared.requests.PatientReq;
import ensaf.pfa.projet.RitiDia.shared.responses.PatientRep;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.JsonPath;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/patients")
public class PatientController {
    @Autowired
    PatientService patientService;
    @Autowired
    PatientRepository patientRepository;


    @PostMapping("/createPatient")
    public ResponseEntity<Object> createPatient(@RequestBody PatientReq patientReq){
        PatientDto patientDto = new PatientDto();
        try{
            BeanUtils.copyProperties(patientReq,patientDto);
            PatientDto createdPatient = patientService.createPatient(patientDto);
            PatientRep patientRep = new PatientRep();
            BeanUtils.copyProperties(createdPatient, patientRep);
            patientRep.setMessage("Le patient a été bien ajouté!");
            return new ResponseEntity<>(patientRep,HttpStatus.CREATED);
        }catch(RuntimeException re){
            return new ResponseEntity<>(re.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getSinglePatient(@PathVariable("id") Long id){

        return new ResponseEntity<>(patientService.getSinglePatient(id),HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity<Object> getPatients(){
        return new ResponseEntity<>(patientService.getPatients(),HttpStatus.OK);
    }

    //Delegation call to Controls
//    @JsonPath("/{id}/controls")
//    public ControlResource ControlsDelegation(){
//        return new ControlResource();
//    }

}
