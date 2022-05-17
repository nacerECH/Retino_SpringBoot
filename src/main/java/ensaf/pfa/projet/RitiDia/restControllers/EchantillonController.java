package ensaf.pfa.projet.RitiDia.restControllers;


import ensaf.pfa.projet.RitiDia.services.implementations.EchantillonService;
import ensaf.pfa.projet.RitiDia.shared.dto.EchantillonDetailsDto;
import ensaf.pfa.projet.RitiDia.shared.requests.EchantillonIndexedRequest;
import ensaf.pfa.projet.RitiDia.shared.requests.SetStadeEchantillon;
import ensaf.pfa.projet.RitiDia.shared.responses.EchantillonResponce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
///controls/{id}/aquisitions
@RequestMapping("/Echantillon")
public class EchantillonController {

    @Autowired
    EchantillonService echantillonService;

    @GetMapping("/NotIndexedEchantillons")
    public ResponseEntity<Object> getNotIndexedEchantillons(){
        Collection<EchantillonResponce> echantillonResponces = echantillonService.GetNotIndexedEchantillons();
        return new ResponseEntity<>(echantillonResponces, HttpStatus.OK);
    }


    @GetMapping("/NotIndexedEchantillons/{echantillon_id}")
    public ResponseEntity<Object> NotIndexedEchantillonDetails(@PathVariable("echantillon_id") Long id){
        EchantillonDetailsDto echantillonDetailsDto = echantillonService.getSingleEchantillon(id);

        return new ResponseEntity<>(echantillonDetailsDto, HttpStatus.OK);
    }

    @PostMapping("/NotIndexedEchantillons/{echantillon_id}/{Index}")
    public ResponseEntity<?> SetIndex(SetStadeEchantillon setStadeEchantillon){
        echantillonService.SetStadeEchantillon(setStadeEchantillon);
        return new ResponseEntity<>("Indexed successfuly",HttpStatus.OK);
    }

    @PostMapping("/Acquisition")
    public  ResponseEntity<?> SaveEchantillon(EchantillonIndexedRequest echantillonIndexedRequest){
        echantillonService.SaveIndexedEchantillon(echantillonIndexedRequest);

        return new ResponseEntity<>("Saved successfuly",HttpStatus.OK);
    }
}
