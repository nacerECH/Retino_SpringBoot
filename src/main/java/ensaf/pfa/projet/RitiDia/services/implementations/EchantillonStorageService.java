package ensaf.pfa.projet.RitiDia.services.implementations;


import ensaf.pfa.projet.RitiDia.Repositories.EchantillonRepository;
import ensaf.pfa.projet.RitiDia.Repositories.ImageRepository;
import ensaf.pfa.projet.RitiDia.Repositories.MedcinRepository;
import ensaf.pfa.projet.RitiDia.entities.enumerations.Eye;
import ensaf.pfa.projet.RitiDia.services.interfaces.IEchantillonStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service

public class EchantillonStorageService implements IEchantillonStorageService {




    private final Path root = Paths.get("src/main/resources/uploads/Collection");
    private final static int COUNTER = 0;

    @Override
    public void init() {
        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    @Override
    public String[] save(MultipartFile file) {

        try {
            String filename = file.getOriginalFilename();
            String[] filename_split = filename.split("\\.");
            String[] stored_filename = new String[2];

            if (filename_split[0].endsWith("OG")) {
                stored_filename[0] = filename.replace(filename_split[0],
                        String.valueOf(System.currentTimeMillis()) + "_left");
                stored_filename[1] = String.valueOf(Eye.LEFT);


            } else if (filename_split[0].endsWith("OD")) {
                stored_filename[0] = filename.replace(filename_split[0],
                        String.valueOf(System.currentTimeMillis()) + "_right");
                stored_filename[1] = String.valueOf(Eye.RIGHT);

            } else {
                System.out.println("cette photo est inapproprié");
            }

            Files.copy(file.getInputStream(),
                    this.root.resolve(stored_filename[0]));
            return stored_filename;

        } catch (IOException e) {
            throw new RuntimeException("could not store the file. Error: " + e.getMessage() + " caused by: " + e.getCause());
        }
    }

    @Override
    public Resource load(String filename) {

        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteAll() {

    }


}
