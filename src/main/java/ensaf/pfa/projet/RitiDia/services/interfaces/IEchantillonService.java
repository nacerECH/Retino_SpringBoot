package ensaf.pfa.projet.RitiDia.services.interfaces;

import ensaf.pfa.projet.RitiDia.entities.Echantillon;

import ensaf.pfa.projet.RitiDia.shared.dto.EchantillonDetailsDto;
import ensaf.pfa.projet.RitiDia.shared.requests.EchantillonIndexedRequest;
import ensaf.pfa.projet.RitiDia.shared.requests.SetStadeEchantillon;
import ensaf.pfa.projet.RitiDia.shared.responses.EchantillonResponce;


import java.util.Collection;

public interface IEchantillonService {

    public Collection<EchantillonResponce> GetNotIndexedEchantillons();

    public EchantillonDetailsDto getSingleEchantillon(Long id);

    public void SetStadeEchantillon(SetStadeEchantillon setStadeEchantillon);

    public void SaveIndexedEchantillon(EchantillonIndexedRequest echantillonIndexedRequest);




}
