package space.damirka.DhBackendServer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import space.damirka.DhBackendServer.Dtos.CreateTicketDto;
import space.damirka.DhBackendServer.Dtos.ListTicketsDto;
import space.damirka.DhBackendServer.services.TicketService;

import java.util.Objects;

@RestController
@RequestMapping("/tickets/")
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("create/")
    public ResponseEntity<?> createTicket(CreateTicketDto ticketDto) {
        try {
            if(Objects.isNull(ticketDto))
                return ResponseEntity.badRequest().body("Non valid request");
            ticketService.createTicket(ticketDto);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("list/")
    public ResponseEntity<?> listTickets(ListTicketsDto listTicketsDto) {
        try {
            return ResponseEntity.ok(ticketService.listTickets(listTicketsDto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
