package ensaf.pfa.projet.RitiDia.Repositories;

import ensaf.pfa.projet.RitiDia.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {



}
