package ensaf.pfa.projet.RitiDia.restControllers;

import ensaf.pfa.projet.RitiDia.services.implementations.AquisitionsStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/controls/{id}/aquisitions")
public class AquisitionController {

    @Autowired
    AquisitionsStorageService filesStorageService;


    @PostMapping("/upload")
    public ResponseEntity<?> uploadAquisition(@PathVariable("id") Long id, @RequestParam("files")MultipartFile[] files){

        String message = "";
        try{
            List<String> fileNames = new ArrayList<>();
            Arrays.asList(files).stream().forEach(file -> {
                filesStorageService.save(file);
                fileNames.add(file.getOriginalFilename());
            });
            message = "Uploaded the files successfully: " + fileNames;
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }catch (Exception e) {
            message = "Fail to upload files!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }

    }


}
