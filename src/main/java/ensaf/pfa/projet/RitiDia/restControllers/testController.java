package ensaf.pfa.projet.RitiDia.restControllers;

import ensaf.pfa.projet.RitiDia.services.implementations.MedcinService;
import ensaf.pfa.projet.RitiDia.services.implementations.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class testController {

    @Autowired
    PatientService patientService;

    @GetMapping("/medcins/{id}/patients")
    public ResponseEntity<?> getPatientsByMedcin(@PathVariable("id") Long id){
        return new ResponseEntity<>(patientService.getPatientsByMedcinId(id), HttpStatus.OK);
    }
}
