package space.damirka.DhBackendServer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import space.damirka.DhBackendServer.entities.AdminEntity;
import space.damirka.DhBackendServer.entities.OSIEntity;

public interface OsiRepository extends JpaRepository<OSIEntity, Long> {

    OSIEntity findOneByAdmin(AdminEntity admin);
}
