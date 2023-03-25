package space.damirka.DhBackendServer.models.auth;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.IDENTITY;


@Entity
@Data
public class Users {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String fullname;

    private String iin;

    private String roles;

    private String password;


}
