package space.damirka.DhBackendServer.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import space.damirka.DhBackendServer.dtos.EditTicketDto;
import space.damirka.DhBackendServer.entities.AdminEntity;
import space.damirka.DhBackendServer.entities.OSIEntity;
import space.damirka.DhBackendServer.entities.StatusEntity;
import space.damirka.DhBackendServer.entities.TicketEntity;
import space.damirka.DhBackendServer.repositories.*;

import java.rmi.NoSuchObjectException;
import java.util.Collections;
import java.util.Date;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;
    private final HouseRepository houseRepository;
    private final OsiRepository osiRepository;
    private final AdminRepository adminRepository;

    private final StatusRepository statusRepository;
    private final TicketRepository ticketRepository;


    public OSIEntity getOsi(String iin) throws NoSuchObjectException {
        AdminEntity admin = adminRepository.findOneByIin(iin);

        if(Objects.isNull(admin))
            throw new NoSuchObjectException("Can't find admin with your iin");

        OSIEntity osi = osiRepository.findOneByAdmin(admin);

        return osi;
    }

    public boolean editTicket(EditTicketDto ticketDto) {
        TicketEntity ticket = ticketRepository.findById(ticketDto.getId()).orElse(null);

        if(Objects.nonNull(ticket)) {
            if(Objects.nonNull(ticketDto.getSubject()))
                ticket.setSubject(ticketDto.getSubject());

            if(Objects.nonNull(ticketDto.getDescription()))
                ticket.setDescription(ticketDto.getDescription());

            if(Objects.nonNull(ticketDto.getAdminResponse()))
                ticket.setAdminResponse(ticketDto.getAdminResponse());

            if(Objects.nonNull(ticketDto.getNewStatus()))
            {
                StatusEntity statusEntity = new StatusEntity(new Date());
                statusEntity.setTitle(ticketDto.getNewStatus());
                statusEntity.setType(ticketDto.getNewStatus());

                statusEntity = statusRepository.save(statusEntity);

                ticket.getStatuses().add(statusEntity);
            }

            ticketRepository.save(ticket);
            return true;
        }
        return false;
    }
}
