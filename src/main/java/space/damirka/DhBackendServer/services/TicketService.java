package space.damirka.DhBackendServer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.damirka.DhBackendServer.Dtos.CreateTicketDto;
import space.damirka.DhBackendServer.entities.StatusEntity;
import space.damirka.DhBackendServer.entities.TicketEntity;
import space.damirka.DhBackendServer.repositories.TicketRepository;

import java.util.Collections;
import java.util.Date;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public void createTicket(CreateTicketDto ticketDto) {
        TicketEntity ticket = new TicketEntity();
        ticket.setSubject(ticket.getSubject());
        ticket.setProblem(ticket.getProblem());
        ticket.setCreated(new Date());
        ticket.setStatuses(Collections.singletonList(new StatusEntity()));

        ticketRepository.save(ticket);
    }
}
