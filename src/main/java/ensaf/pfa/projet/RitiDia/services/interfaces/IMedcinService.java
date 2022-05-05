package ensaf.pfa.projet.RitiDia.services.interfaces;

import ensaf.pfa.projet.RitiDia.shared.dto.PatientDto;
import ensaf.pfa.projet.RitiDia.shared.dto.RegistredMedcinDto;

public interface IMedcinService {
    public RegistredMedcinDto createMedcin(RegistredMedcinDto medcin);
}
