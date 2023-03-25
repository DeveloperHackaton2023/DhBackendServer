package space.damirka.DhBackendServer.dtos;

import lombok.Data;

@Data
public class UserAddTicketHouseDto {

    private String iin;

    private String telephone;

    private Long houseId;

    private String subject;

    private String description;
}
