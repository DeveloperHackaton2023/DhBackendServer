package space.damirka.DhBackendServer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import space.damirka.DhBackendServer.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}