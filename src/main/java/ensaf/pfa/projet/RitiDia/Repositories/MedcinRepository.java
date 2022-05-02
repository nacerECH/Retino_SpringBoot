package ensaf.pfa.projet.RitiDia.Repositories;

import ensaf.pfa.projet.RitiDia.entities.Medcin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedcinRepository extends JpaRepository<Medcin, Long> {

    Medcin findByInpe(String inpe);
    Medcin findByEmail(String email);

}
