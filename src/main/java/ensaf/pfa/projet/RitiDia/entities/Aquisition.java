package ensaf.pfa.projet.RitiDia.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import ensaf.pfa.projet.RitiDia.entities.enumerations.Eye;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor

public class Aquisition {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uuid;
    private String url;
    @Enumerated(EnumType.STRING)
    private Eye type_oeil;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Control controle;
}
