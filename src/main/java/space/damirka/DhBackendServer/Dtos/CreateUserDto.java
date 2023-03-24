package space.damirka.DhBackendServer.Dtos;

import lombok.Data;

import java.util.List;

@Data
public class CreateUserDto {

    private String iin;
    private String telephone;
    private String fullname;
    private String address;

    private List<CreateHouseDto> houses;

    private Long osiId;

}
