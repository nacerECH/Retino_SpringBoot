package ensaf.pfa.projet.RitiDia.restControllers;

import ensaf.pfa.projet.RitiDia.services.implementations.MedcinService;
import ensaf.pfa.projet.RitiDia.shared.dto.RegistredMedcinDto;
import ensaf.pfa.projet.RitiDia.shared.requests.RegistredMedcinReq;

import ensaf.pfa.projet.RitiDia.shared.responses.RegistredMedcinRep;
//import ensaf.pfa.projet.RitiDia.webSecurity.models.AuthenticationRequest;
//import ensaf.pfa.projet.RitiDia.webSecurity.models.AuthenticationResponse;
//import ensaf.pfa.projet.RitiDia.webSecurity.service.AppUserDetailsService;
//import ensaf.pfa.projet.RitiDia.webSecurity.util.JwtUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class MedcinController {

    @Autowired
    private MedcinService medcinService;
//    @Autowired
//    private AuthenticationManager authenticationManager;
//    @Autowired
//    private AppUserDetailsService appUserDetailsService;
//    @Autowired
//    private JwtUtil jwtUtil;

    @PostMapping("/createMedcin")
    public ResponseEntity<Object> createMedcin(@RequestBody RegistredMedcinReq m){

        RegistredMedcinDto medcin = new RegistredMedcinDto();
        try{
            BeanUtils.copyProperties(m,medcin);
            RegistredMedcinDto saved_medcin = medcinService.createMedcin(medcin);
            RegistredMedcinRep resulted_medcin = new RegistredMedcinRep();
            BeanUtils.copyProperties(saved_medcin,resulted_medcin);
            return new ResponseEntity<>(HttpStatus.OK).ok(resulted_medcin);
        }catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.IM_USED);
        }

    }
//    @PostMapping("/authenticate")
//    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authRequest) throws Exception {
//        try{
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword())
//            );
//
//        }catch(BadCredentialsException e){
//            throw new Exception("Incorrect username or password",e );
//        }
//        final UserDetails userDetails = appUserDetailsService.loadUserByUsername(authRequest.getUsername());
//        final String jwt= jwtUtil.generateToken(userDetails);
//        return ResponseEntity.ok(new AuthenticationResponse(jwt));
//
//    }

}
