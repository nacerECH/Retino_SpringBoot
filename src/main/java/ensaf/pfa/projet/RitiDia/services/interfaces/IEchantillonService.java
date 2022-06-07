package ensaf.pfa.projet.RitiDia.services.interfaces;

import ensaf.pfa.projet.RitiDia.shared.dto.EchantillonDetailsDto;
import ensaf.pfa.projet.RitiDia.shared.requests.EchantillonIndexedRequest;
import ensaf.pfa.projet.RitiDia.shared.requests.SetStadeEchantillon;
import ensaf.pfa.projet.RitiDia.shared.responses.EchantillonDetailsResponse;
import ensaf.pfa.projet.RitiDia.shared.responses.EchantillonResponce;
import org.springframework.web.multipart.MultipartFile;


import java.util.Collection;
import java.util.List;

public interface IEchantillonService {

    public Collection<EchantillonResponce> GetNotIndexedEchantillons(Long medcin_id);

    public EchantillonDetailsDto getSingleEchantillon(Long id);

    public void SetStadeEchantillon(SetStadeEchantillon setStadeEchantillon);

    public EchantillonDetailsResponse SaveIndexedEchantillon(EchantillonIndexedRequest echantillonIndexedRequest, MultipartFile[] files);




}
