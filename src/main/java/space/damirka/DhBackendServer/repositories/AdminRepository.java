package space.damirka.DhBackendServer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import space.damirka.DhBackendServer.entities.AdminEntity;

public interface AdminRepository extends JpaRepository<AdminEntity, Long> {

    AdminEntity findOneByIin(String iin);
}
