package space.damirka.DhBackendServer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import space.damirka.DhBackendServer.entities.UserEntity;
import space.damirka.DhBackendServer.entities.UserHouseEntity;

import java.util.List;

public interface HouseRepository extends JpaRepository<UserHouseEntity, Long> {

    List<UserHouseEntity> findAllByUser(UserEntity user);
}
