package ensaf.pfa.projet.RitiDia.Repositories;

import ensaf.pfa.projet.RitiDia.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
//    Patient findByNom(String nom);
    Patient findByCin(String cin);
//    @Query("select p from Patient p order by p.controles.size")
//    List<Patient> findAllOrderByDateControl();
    Optional<Patient> findById(Long id);

    @Query("select p from Patient p where p.medcinID = ?1 and (p.nom like %?2% or p.prenom like %?2% or p.cin like %?2% )")
    List<Patient> findPatientsByMedcinIdAndPattern(Long id,String creteria);

    Patient findByIdAndMedcinID(Long id, Long medcinID);
}
