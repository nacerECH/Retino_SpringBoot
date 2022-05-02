package ensaf.pfa.projet.RitiDia.Repositories;

import ensaf.pfa.projet.RitiDia.entities.Aquisition;
import ensaf.pfa.projet.RitiDia.entities.enumerations.Eye;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
@Repository
public interface AquisitionRepository extends JpaRepository<Aquisition,Long> {

    @Query("select aq from Aquisition aq where aq.type_oeil =:eye_type and aq.controle.id =:id")
    public Collection<Aquisition> findByControle_IdAndType_oeil(@Param("id") Long id,@Param("eye_type") Eye type_oeil);
}
