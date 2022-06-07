package ensaf.pfa.projet.RitiDia.services.implementations;


import ensaf.pfa.projet.RitiDia.Repositories.EchantillonRepository;
import ensaf.pfa.projet.RitiDia.Repositories.ImageRepository;
import ensaf.pfa.projet.RitiDia.Repositories.MedcinRepository;
import ensaf.pfa.projet.RitiDia.entities.Echantillon;
import ensaf.pfa.projet.RitiDia.entities.Image;
import ensaf.pfa.projet.RitiDia.entities.Medcin;

import ensaf.pfa.projet.RitiDia.services.interfaces.IEchantillonService;
import ensaf.pfa.projet.RitiDia.shared.dto.EchantillonDetailsDto;

import ensaf.pfa.projet.RitiDia.shared.dto.ImageDto;
import ensaf.pfa.projet.RitiDia.shared.requests.EchantillonIndexedRequest;
import ensaf.pfa.projet.RitiDia.shared.requests.SetStadeEchantillon;
import ensaf.pfa.projet.RitiDia.shared.responses.EchantillonDetailsResponse;
import ensaf.pfa.projet.RitiDia.shared.responses.EchantillonResponce;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;



import java.util.*;

@Service
@Transactional
public class EchantillonService implements IEchantillonService {

    @Autowired
    EchantillonRepository echantillonRepository;

    @Autowired
    MedcinRepository medcinRepository;

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    EchantillonStorageService  echantillonStorageService;



    @Override
    public Collection<EchantillonResponce> GetNotIndexedEchantillons(Long medcin_id) {


      // List<Echantillon> echantillons = entityManager.createQuery(jpql, Echantillon.class).getResultList();
        Collection<Echantillon> echantillons = echantillonRepository.GetNotIndexedEchantillons(medcin_id);

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
            echantillonRepository.save(echantillon);

        }


    }

    @Override
    public EchantillonDetailsResponse SaveIndexedEchantillon(EchantillonIndexedRequest echantillonIndexedRequest , MultipartFile[] files  ) {

        EchantillonDetailsResponse echantillonDetailsResponse = new EchantillonDetailsResponse();
        List<String> fileNames = new ArrayList<>();
        if(medcinRepository.findById(echantillonIndexedRequest.getMedcinID())==null) throw new RuntimeException(".. ");
        else {
            Echantillon echantillon = new Echantillon();


            BeanUtils.copyProperties(echantillonIndexedRequest, echantillon);

          Medcin medcin =  medcinRepository.getById(echantillonIndexedRequest.getMedcinID());


          echantillon.setMedcin(medcin);
            Date date = new Date();

            echantillon.setDate_acquisition(date);





            Collection<Image> images = new ArrayList<>();

            Arrays.asList(files).stream().forEach(file -> {
                String[]  saved_file_info = echantillonStorageService.save(file);
                Image image = new Image();
                image.setUrl(saved_file_info[0]);
                image.setEchantillon(echantillon);
                imageRepository.save(image);
                images.add(image);
                fileNames.add(saved_file_info[0]);
            });

            echantillon.setImages(images);

            echantillon.setStade(echantillonIndexedRequest.getStade());


            Collection<ImageDto> imageDtos = new ArrayList<>();

           // Collection<Image> images = new ArrayList<>();

          //  Medcin medcin = medcinRepository.getById(echantillon.getMedcin());
           // echantillon.setMedcin(medcin);

            images.forEach(image -> {
                ImageDto imageDto = new ImageDto();
                BeanUtils.copyProperties(image,imageDto);
                imageDtos.add(imageDto);

            });



            echantillonRepository.save(echantillon);
            BeanUtils.copyProperties(echantillon,echantillonDetailsResponse);

            echantillonDetailsResponse.setImagesDto(imageDtos);

           // echantillon.setImages(images);





        }



        return echantillonDetailsResponse;
    }


}
