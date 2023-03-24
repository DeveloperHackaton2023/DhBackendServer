package space.damirka.DhBackendServer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import space.damirka.DhBackendServer.entities.StatusEntity;

public interface StatusRepository extends JpaRepository<StatusEntity, Long> {
}
