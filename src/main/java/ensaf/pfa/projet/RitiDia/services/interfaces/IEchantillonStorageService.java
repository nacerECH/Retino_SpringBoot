package ensaf.pfa.projet.RitiDia.services.interfaces;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IEchantillonStorageService {

    public void init();

    public String[] save(MultipartFile file);

    public Resource load(String filename);

    public void deleteAll();

}
