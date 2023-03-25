package space.damirka.DhBackendServer.dtos;

import lombok.Data;

@Data
public class RespondUserInfoDto {

    private Long id;
    private String iin;
    private String telephone;
    private String fullname;
    private String roles;
}
