package space.damirka.DhBackendServer.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity
@Data
public class StatusEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    private StatusEnum type;
    @Enumerated(EnumType.STRING)
    private StatusEnum title;

    @CreatedDate
    private Date Created;

    public StatusEntity()
    {
        this.type = StatusEnum.Created;
        this.title = StatusEnum.Created;
    }

    public StatusEntity(Date created)
    {
        this.type = StatusEnum.Created;
        this.title = StatusEnum.Created;
        this.Created = created;
    }


}
