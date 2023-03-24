package space.damirka.DhBackendServer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.damirka.DhBackendServer.Dtos.CreateTicketDto;
import space.damirka.DhBackendServer.entities.StatusEntity;
import space.damirka.DhBackendServer.entities.TicketEntity;
import space.damirka.DhBackendServer.repositories.StatusRepository;
import space.damirka.DhBackendServer.repositories.TicketRepository;

import java.util.Collections;
import java.util.Date;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    private final StatusRepository statusRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository, StatusRepository statusRepository) {
        this.ticketRepository = ticketRepository;
        this.statusRepository = statusRepository;
    }

    public void createTicket(CreateTicketDto ticketDto) {
        TicketEntity ticket = new TicketEntity();
        ticket.setSubject(ticketDto.getSubject());
        ticket.setDescription(ticketDto.getDescription());

        Date now = new Date();
        ticket.setCreated(now);

        StatusEntity statusEntity = new StatusEntity(now);

        statusEntity = statusRepository.save(statusEntity);

        ticket.setStatuses(Collections.singletonList(statusEntity));

        ticketRepository.save(ticket);
    }
}
