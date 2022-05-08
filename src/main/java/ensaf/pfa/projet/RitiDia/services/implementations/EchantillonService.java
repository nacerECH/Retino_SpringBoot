package ensaf.pfa.projet.RitiDia.services.implementations;


import ensaf.pfa.projet.RitiDia.Repositories.EchantillonRepository;
import ensaf.pfa.projet.RitiDia.Repositories.ImageRepository;
import ensaf.pfa.projet.RitiDia.Repositories.MedcinRepository;
import ensaf.pfa.projet.RitiDia.entities.Echantillon;
import ensaf.pfa.projet.RitiDia.entities.Image;
import ensaf.pfa.projet.RitiDia.entities.Medcin;
import ensaf.pfa.projet.RitiDia.entities.enumerations.Stade;
import ensaf.pfa.projet.RitiDia.services.interfaces.IEchantillonService;
import ensaf.pfa.projet.RitiDia.shared.dto.EchantillonDetailsDto;
import ensaf.pfa.projet.RitiDia.shared.dto.ImageDto;
import ensaf.pfa.projet.RitiDia.shared.requests.EchantillonIndexedRequest;
import ensaf.pfa.projet.RitiDia.shared.requests.SetStadeEchantillon;
import ensaf.pfa.projet.RitiDia.shared.responses.EchantillonResponce;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Service
@Transactional
public class EchantillonService implements IEchantillonService {

    @Autowired
    EchantillonRepository echantillonRepository;

    @Autowired
    MedcinRepository medcinRepository;

    @Autowired
    ImageRepository imageRepository;

    @Override
    public Collection<EchantillonResponce> GetNotIndexedEchantillons() {

        Collection<Echantillon> echantillons = echantillonRepository.GetNotIndexedEchantillons(Stade.NOT_INDEXED);

        Collection<EchantillonResponce> echantillonResponces = new ArrayList<>();

        echantillons.forEach(echantillon -> {
            EchantillonResponce echantillonResponce = new EchantillonResponce();
            BeanUtils.copyProperties(echantillon,echantillonResponce);
            echantillonResponces.add(echantillonResponce);

        });

        return echantillonResponces;
    }

    @Override
    public EchantillonDetailsDto getSingleEchantillon(Long id) {

        if(echantillonRepository.findById(id) == null) throw new RuntimeException(".. ");
        else{

            Echantillon echantillon = echantillonRepository.findById(id).get();
            EchantillonDetailsDto echantillonDetailsDto = new EchantillonDetailsDto();
            echantillonDetailsDto.setId(echantillon.getId());
            BeanUtils.copyProperties(echantillon,echantillonDetailsDto);

            Collection<Image> images = echantillon.getImages();

            Collection<ImageDto> imageDtos = new ArrayList<>();

            images.forEach(image -> {
                ImageDto imageDto = new ImageDto();
                BeanUtils.copyProperties(image,imageDto);
                imageDtos.add(imageDto);

            });

            echantillonDetailsDto.setImageDtos(imageDtos);


            return  echantillonDetailsDto;




        }

    }

    @Override
    public void SetStadeEchantillon(SetStadeEchantillon setStadeEchantillon) {

        if(echantillonRepository.findById(setStadeEchantillon.getId()) == null) throw new RuntimeException(".. ");
        else{
            Echantillon echantillon = echantillonRepository.getById(setStadeEchantillon.getId());
            echantillon.setStade(setStadeEchantillon.getStade());

        }


    }

    @Override
    public void SaveIndexedEchantillon(EchantillonIndexedRequest echantillonIndexedRequest) {

        if(medcinRepository.findById(echantillonIndexedRequest.getMedcinID())==null) throw new RuntimeException(".. ");
        else {
            Echantillon echantillon = new Echantillon();
            BeanUtils.copyProperties(echantillonIndexedRequest, echantillon);

            echantillon.setDate_acquisition(new Date());

            echantillon.setStade(echantillonIndexedRequest.getStade());

            Collection<ImageDto> imageDtos = echantillonIndexedRequest.getImageDtos();

            Collection<Image> images = new ArrayList<>();

            Medcin medcin = medcinRepository.getById(echantillonIndexedRequest.getMedcinID());
            echantillon.setMedcin(medcin);

            imageDtos.forEach(imageDto -> {
                Image image = new Image();
                BeanUtils.copyProperties(imageDto, image);
                images.add(image);

            });

            echantillon.setImages(images);
            echantillonRepository.save(echantillon);

        }

    }


}
