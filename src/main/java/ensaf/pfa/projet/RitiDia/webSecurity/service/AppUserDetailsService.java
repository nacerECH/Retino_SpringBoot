//package ensaf.pfa.projet.RitiDia.webSecurity.service;
//import ensaf.pfa.projet.RitiDia.Repositories.MedcinRepository;
//import ensaf.pfa.projet.RitiDia.entities.Medcin;
//import ensaf.pfa.projet.RitiDia.webSecurity.models.AppUserDetails;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import java.util.Optional;
//
//@EnableWebSecurity
//public class AppUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    MedcinRepository medcinRepository;
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//
//        Optional<Medcin> medcin = Optional.ofNullable(medcinRepository.findByEmail(email));
//        medcin.orElseThrow(() -> new UsernameNotFoundException("Not found: "+ email));
//
//        AppUserDetails appUserDetails = medcin.map(AppUserDetails::new).get();
//
//        return appUserDetails ;
//
//
//    }
//}
//
