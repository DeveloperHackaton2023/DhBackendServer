package space.damirka.DhBackendServer.entities;

import jakarta.persistence.*;
import lombok.Data;


import java.util.Date;
import java.util.List;

@Entity
@Data
public class TicketEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String Subject;

    private String Description;

    private Date Created;

    @OneToMany(fetch = FetchType.EAGER)
    List<StatusEntity> statuses;

    private String AdminResponse;

    //public List<File> Files { get; set; }

}
