package space.damirka.DhBackendServer.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Admin
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String iin;
    private String telephone;
    private String fullname;

    @OneToMany
    private List<OSI> Osis;

}
