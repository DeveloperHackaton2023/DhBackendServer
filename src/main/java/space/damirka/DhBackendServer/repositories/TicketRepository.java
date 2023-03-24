package space.damirka.DhBackendServer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import space.damirka.DhBackendServer.entities.TicketEntity;


public interface TicketRepository extends JpaRepository<TicketEntity, Long> {

}
