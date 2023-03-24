package space.damirka.DhBackendServer.Dtos;

import lombok.Data;

@Data
public class CreateHouseDto {

    private String address;

    private String flatNumber;

    private String info;
}
