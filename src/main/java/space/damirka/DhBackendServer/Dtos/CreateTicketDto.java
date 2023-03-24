package space.damirka.DhBackendServer.Dtos;

import lombok.Data;

@Data
public class CreateTicketDto {
    private String subject;
    private String description;
    private String iin;
    private String telephone;
}
