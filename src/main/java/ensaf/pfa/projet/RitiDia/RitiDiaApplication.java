package ensaf.pfa.projet.RitiDia;

import ensaf.pfa.projet.RitiDia.Repositories.AquisitionRepository;
import ensaf.pfa.projet.RitiDia.Repositories.ControlRepository;
import ensaf.pfa.projet.RitiDia.Repositories.PatientRepository;
import ensaf.pfa.projet.RitiDia.Repositories.StadePatientRepository;
import ensaf.pfa.projet.RitiDia.entities.Patient;
import ensaf.pfa.projet.RitiDia.services.implementations.AquisitionsStorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class RitiDiaApplication implements CommandLineRunner {

	@Resource
	AquisitionsStorageService storageService;

	public static void main(String[] args) {
		SpringApplication.run(RitiDiaApplication.class, args);
	}

//	CommandLineRunner start(PatientRepository patientRepository, ControlRepository controlRepository, AquisitionRepository aquisitionRepository,
//							StadePatientRepository stadePatientRepository){
//		return args -> {
//			Stream.of("Mohamed", "Hassan", "Najat")
//					.forEach(name-> {
//						Patient patient= new Patient();
//						patient.setCin("CD8554");
//						patient.setNom(name);
//						patient.setPrenom("tokka");
//						patient.setDate_naissance(new Date());
//						patient.setEst_diabetique(true);
//						patient.setSexe("homme");
//						patient.setTel("0682671698");
//						patientRepository.save(patient);
//					});
//
//		};
//
//	}

	@Override
	public void run(String... arg) throws Exception {
		storageService.deleteAll();
		storageService.init();
	}







}
