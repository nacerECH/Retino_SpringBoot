package ensaf.pfa.projet.RitiDia.restControllers;

import ensaf.pfa.projet.RitiDia.services.implementations.ControlService;
import ensaf.pfa.projet.RitiDia.shared.requests.StadeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/medcins/{medcin_id}/patients/{patient_id}")
public class ControlCreationController {

    @Autowired
    ControlService controlService;



    @PostMapping("/addControl")
    public ResponseEntity<?> createControl(@PathVariable("medcin_id") Long medcin_id,
                                           @PathVariable("patient_id") Long patient_id,
                                           @RequestParam("images")MultipartFile[] aquisitions,
                                           @RequestParam("sog") String sog,
                                           @RequestParam("sod") String sod
                                          ){


        String message = "";
        try{
            List<String> fileNames = new ArrayList<>();
            controlService.addControl(medcin_id,patient_id,aquisitions, sod, sog);
            message = "Uploaded the files successfully: " + fileNames;
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }catch (Exception e) {
            message = "ERROR : "+e.getMessage() +"\n CAUSE : "+e.getCause();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }

}
