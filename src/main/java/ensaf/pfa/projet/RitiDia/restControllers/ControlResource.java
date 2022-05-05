package ensaf.pfa.projet.RitiDia.restControllers;

import ensaf.pfa.projet.RitiDia.services.implementations.ControlService;
import ensaf.pfa.projet.RitiDia.shared.dto.ControlDetailsDto;
import ensaf.pfa.projet.RitiDia.shared.dto.ControlResponseDto;
import ensaf.pfa.projet.RitiDia.shared.dto.HalfControlDetailsDto;
import ensaf.pfa.projet.RitiDia.shared.requests.ControlRequest;
import ensaf.pfa.projet.RitiDia.shared.requests.ControlWithBilanReq;
import ensaf.pfa.projet.RitiDia.shared.responses.ControlResponse;
import ensaf.pfa.projet.RitiDia.shared.responses.ControlWithBilanRep;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/patients/{id}/controls")
public class ControlResource {
    @Autowired
    ControlService controlService;

    @GetMapping("/{control_id}")
    public ResponseEntity<Object> getHalfControl(@PathVariable("control_id") Long id){
        HalfControlDetailsDto control = controlService.getHalfControl(id);
        return new ResponseEntity<>(control, HttpStatus.OK);
    }
    @GetMapping("/{control_id}/details")
    public ResponseEntity<Object> getSingleControl(@PathVariable("control_id") Long id){
        ControlDetailsDto control = controlService
                .getSingleControl(id);
        return new ResponseEntity<>(control,HttpStatus.OK);
    }

    @PutMapping("/{control_id}/update")
    public ResponseEntity<Object> setBilanToControl(@PathVariable("control_id") Long id, @RequestBody ControlWithBilanReq control){
        ControlWithBilanRep response = controlService.updateControl(id,control);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/createControl")
    public ResponseEntity<?> createControl(ControlRequest controlRequest){
        ControlResponseDto controlResponseDto = controlService.createControl(controlRequest);
        ControlResponse controlResponse = new ControlResponse();
        BeanUtils.copyProperties(controlResponseDto,controlResponse);

        return new ResponseEntity<>(controlResponse,HttpStatus.OK);
    }
}
