package space.damirka.DhBackendServer.models.requests;

import lombok.Data;

@Data
public class UsersRequest {
    private String iin;
    private String fullname;
    private String password;
    private String roles;
}
