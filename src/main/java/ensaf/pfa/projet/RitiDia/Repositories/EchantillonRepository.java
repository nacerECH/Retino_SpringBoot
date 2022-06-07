package ensaf.pfa.projet.RitiDia.Repositories;


import ensaf.pfa.projet.RitiDia.entities.Echantillon;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;


@Repository
public interface EchantillonRepository extends JpaRepository<Echantillon, Long> {


    @Query("select ech from Echantillon ech where  ech.stade = ensaf.pfa.projet.RitiDia.entities.enumerations.Stade.NOT_INDEXED and ech.medcin.id=:medcin_id ORDER BY ech.date_acquisition")
    public Collection<Echantillon> GetNotIndexedEchantillons(@Param("medcin_id") Long medcin_id);


}
