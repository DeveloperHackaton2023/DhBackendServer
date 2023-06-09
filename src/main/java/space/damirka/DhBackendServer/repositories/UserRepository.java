package space.damirka.DhBackendServer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import space.damirka.DhBackendServer.entities.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findOneByIinAndTelephone(String iin, String telephone);
    UserEntity findOneByIin(String iin);
}
