package space.damirka.DhBackendServer.dtos;

import lombok.Data;
import space.damirka.DhBackendServer.entities.StatusEnum;

@Data
public class EditTicketDto {

    private Long id;
    private String subject;
    private String description;
    private String adminResponse;
    private StatusEnum newStatus;
}
