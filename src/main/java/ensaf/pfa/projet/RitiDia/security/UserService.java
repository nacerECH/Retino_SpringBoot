package ensaf.pfa.projet.RitiDia.security;


import ensaf.pfa.projet.RitiDia.Repositories.MedcinRepository;
import ensaf.pfa.projet.RitiDia.entities.Medcin;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    MedcinRepository medcinRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Medcin medcinEntity =  medcinRepository.findByEmail(email);
        if(medcinEntity == null) throw new UsernameNotFoundException(email);


        return new User(medcinEntity.getEmail(),medcinEntity.getEncryptedPassword(),new ArrayList<>());

    }

    public UserDto getUser(String email){

        Medcin userEntity = medcinRepository.findByEmail(email);

        if(userEntity == null) throw new UsernameNotFoundException(email);
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userEntity,userDto);
        return userDto;
    }
}
