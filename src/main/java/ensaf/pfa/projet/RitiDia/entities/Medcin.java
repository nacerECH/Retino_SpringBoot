package ensaf.pfa.projet.RitiDia.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Medcin {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String uuid;
    @Column(unique = true)
    private String inpe;
    private String nom;
    private String prenom;
    private String encryptedPassword;
    private String numero_pattente;
    private String num_tel;
    private boolean active;
    private String roles;
    @Column(unique = true)
    private String email;
    @OneToMany(mappedBy = "medcin",fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Control> controles;



    public Medcin(Medcin medcin) {
        if(medcin != null){
            BeanUtils.copyProperties(medcin,this);
        }
    }


}

