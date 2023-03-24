package space.damirka.DhBackendServer.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private StatusEnum s;
    private String title;

    @CreatedDate
    private Date Created;

    public StatusEntity()
    {
        this.s = StatusEnum.Created;
        this.title = StatusEnum.Created.toString();
    }

    public StatusEntity(Date created)
    {
        this.s = StatusEnum.Created;
        this.title = StatusEnum.Created.toString();
        this.Created = created;
    }


}
