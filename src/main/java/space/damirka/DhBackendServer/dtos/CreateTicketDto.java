package space.damirka.DhBackendServer.dtos;

import lombok.Data;

@Data
public class CreateTicketDto {
    private String subject;
    private String description;
    private String iin;
    private String telephone;
}
