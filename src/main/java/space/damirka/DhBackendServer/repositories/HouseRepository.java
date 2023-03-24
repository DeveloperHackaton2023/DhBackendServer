package space.damirka.DhBackendServer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import space.damirka.DhBackendServer.entities.UserHouseEntity;

public interface HouseRepository extends JpaRepository<UserHouseEntity, Long> {
}
