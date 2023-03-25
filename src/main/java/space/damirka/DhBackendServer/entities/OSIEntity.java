package space.damirka.DhBackendServer.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private AdminEntity admin;
}