package ensaf.pfa.projet.RitiDia.Repositories;

import ensaf.pfa.projet.RitiDia.entities.Control;
import ensaf.pfa.projet.RitiDia.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ControlRepository extends JpaRepository<Control, Long> {


    @Modifying
    @Query("update Control c set c.bilan = ?1, c.medcin_2 = ?2 where c.id = ?3")
    void updateControlById(String bilan, String medcin_fullname, Long id);

}
