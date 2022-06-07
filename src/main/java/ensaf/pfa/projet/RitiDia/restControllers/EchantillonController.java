package ensaf.pfa.projet.RitiDia.restControllers;


import ensaf.pfa.projet.RitiDia.entities.enumerations.Eye;
import ensaf.pfa.projet.RitiDia.entities.enumerations.Stade;
import ensaf.pfa.projet.RitiDia.services.implementations.EchantillonService;
import ensaf.pfa.projet.RitiDia.shared.dto.EchantillonDetailsDto;
import ensaf.pfa.projet.RitiDia.shared.requests.EchantillonIndexedRequest;
import ensaf.pfa.projet.RitiDia.shared.requests.SetStadeEchantillon;
import ensaf.pfa.projet.RitiDia.shared.responses.EchantillonDetailsResponse;
import ensaf.pfa.projet.RitiDia.shared.responses.EchantillonResponce;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ensaf.pfa.projet.RitiDia.entities.enumerations.Stade;
//import org.apache.commons.io.IOUtils;



import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/Echantillon")
public class EchantillonController {

    @Autowired
    EchantillonService echantillonService;




    @GetMapping("{medcinID}/NotIndexedEchantillons")
    public ResponseEntity<?> getNotIndexedEchantillons(@PathVariable("medcinID") String medcinID){
        Collection<EchantillonResponce> echantillonResponces = echantillonService.GetNotIndexedEchantillons(Long.parseLong(medcinID));
        return new ResponseEntity<Collection<EchantillonResponce>>(echantillonResponces, HttpStatus.OK);
    }





    @GetMapping("/NotIndexedEchantillons/{echantillon_id}")
    public ResponseEntity<?> NotIndexedEchantillonDetails(@PathVariable("echantillon_id") Long id){
        EchantillonDetailsDto echantillonDetailsDto = echantillonService.getSingleEchantillon(id);

        return new ResponseEntity<EchantillonDetailsDto>(echantillonDetailsDto, HttpStatus.OK);
    }

    @PostMapping("/NotIndexedEchantillon/{echantillon_id}")
    public ResponseEntity<?> SetIndex(@PathVariable("echantillon_id") Long echantillon_id ,@RequestParam("stade") String stade){
        SetStadeEchantillon setStadeEchantillon = new SetStadeEchantillon();


        setStadeEchantillon.setId(echantillon_id);
        setStadeEchantillon.setStade(Stade.valueOf(stade));

        echantillonService.SetStadeEchantillon(setStadeEchantillon);
        return new ResponseEntity<String>("Indexed successfuly",HttpStatus.OK);
    }

    @PostMapping("/Acquisition")
    public  ResponseEntity<?> SaveEchantillon( @RequestParam("medcinID") String medcinID ,@RequestParam("eye") String eye , @RequestParam("stade") String stade, @RequestParam("images") MultipartFile[] aquisitions ){

        EchantillonIndexedRequest echantillonIndexedRequest = new EchantillonIndexedRequest();
        EchantillonDetailsResponse echantillonDetailsResponse = new EchantillonDetailsResponse();


        echantillonIndexedRequest.setMedcinID(Long.parseLong(medcinID));

        echantillonIndexedRequest.setEye(Eye.valueOf(eye));
        echantillonIndexedRequest.setStade(Stade.valueOf(stade));
        List<String> images2 = new ArrayList<>();
        echantillonDetailsResponse =  echantillonService.SaveIndexedEchantillon(echantillonIndexedRequest, aquisitions );
        //echantillonService.SaveIndexedEchantillon(echantillonIndexedRequest);

        //return new ResponseEntity<EchantillonIndexedRequest>(echantillonIndexedRequest,HttpStatus.OK);

        return new ResponseEntity<EchantillonDetailsResponse>(echantillonDetailsResponse,HttpStatus.OK);
    }



    @GetMapping(value = "image/{image_name}")
    public ResponseEntity<byte[]> fromClasspathAsResEntity(@PathVariable("image_name") String image_name) throws IOException {

        String url = "uploads/Collection/"+image_name;
        System.out.println(url);
        ClassPathResource imageFile = new ClassPathResource(url);
       // FileResourcesUtils app = new FileResourcesUtils();
        //File imageFile =

        byte[] imageBytes = StreamUtils.copyToByteArray(imageFile.getInputStream());

        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
    }


}
