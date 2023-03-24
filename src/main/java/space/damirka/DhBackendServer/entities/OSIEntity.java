package space.damirka.DhBackendServer.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class OSIEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    private String info;

    @OneToMany(fetch = FetchType.LAZY)
    private List<UserHouseEntity> houses;

    @ManyToOne(fetch = FetchType.LAZY)
    private AdminEntity admin;
}