package space.damirka.DhBackendServer.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Status
{
    public enum StatusEnum {
        Created,
        InProgress,
        Denied,
        Success
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private StatusEnum Title;

    private Date Created;

    public Status()
    {
        this.Title = StatusEnum.Created;
    }
}
