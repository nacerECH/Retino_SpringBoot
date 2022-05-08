package ensaf.pfa.projet.RitiDia.services.implementations;

import ensaf.pfa.projet.RitiDia.Repositories.MedcinRepository;
import ensaf.pfa.projet.RitiDia.entities.Medcin;
import ensaf.pfa.projet.RitiDia.services.interfaces.IMedcinService;
import ensaf.pfa.projet.RitiDia.shared.dto.RegistredMedcinDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Service
public class MedcinService implements IMedcinService {

    @Autowired
    MedcinRepository medcinRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    

    @Override
    public RegistredMedcinDto createMedcin(RegistredMedcinDto medcin) {
        if(medcinRepository.findByInpe(medcin.getInpe()) ==null
                && medcinRepository.findByEmail(medcin.getEmail()) == null){
            Medcin _medcin = new Medcin();
            BeanUtils.copyProperties(medcin,_medcin);
            _medcin.setEncryptedPassword(bCryptPasswordEncoder.encode(medcin.getPassword()));
            _medcin.setUuid(UUID.randomUUID().toString());
            
            Medcin saved_medcin = medcinRepository.save(_medcin);
            RegistredMedcinDto returned_medcin = new RegistredMedcinDto();
            BeanUtils.copyProperties(saved_medcin,returned_medcin);

            return returned_medcin;
        }
        else{
            throw new RuntimeException("Ce medcin est déjà existé!");
        }

    }




    public String encodePassword(String password){
        String encrypted = null;
        try{
            MessageDigest m = MessageDigest.getInstance("Md5");
            m.update(password.getBytes());
            byte[] bytes = m.digest();
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length;i++){
                sb.append(Integer.toString((bytes[i] & 0xff), 16).substring(1));

            }
            encrypted = sb.toString();
        }catch (NoSuchAlgorithmException e){
            e.getMessage();
        }


        return encrypted;
    }
}
