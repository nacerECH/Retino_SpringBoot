package ensaf.pfa.projet.RitiDia.services.interfaces;

import ensaf.pfa.projet.RitiDia.entities.Control;
import ensaf.pfa.projet.RitiDia.shared.dto.ControlDetailsDto;
import ensaf.pfa.projet.RitiDia.shared.dto.ControlResponseDto;
import ensaf.pfa.projet.RitiDia.shared.dto.HalfControlDetailsDto;
import ensaf.pfa.projet.RitiDia.shared.requests.ControlRequest;
import ensaf.pfa.projet.RitiDia.shared.requests.ControlWithBilanReq;
import ensaf.pfa.projet.RitiDia.shared.responses.ControlWithBilanRep;

import java.util.Collection;

public interface IControlService {
    public HalfControlDetailsDto getHalfControl(Long id);
    public ControlDetailsDto getSingleControl(Long id);
    public ControlWithBilanRep updateControl(Long id, ControlWithBilanReq new_control);
    public Collection<Control> getAllControls();
   // public ControlResponseDto createControl(ControlRequest controlRequest);
}
