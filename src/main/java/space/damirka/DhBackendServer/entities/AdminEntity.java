package space.damirka.DhBackendServer.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class AdminEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String iin;
    @Column(unique = true)
    private String telephone;
    private String fullname;

    @OneToMany
    private List<OSIEntity> Osis;

}
