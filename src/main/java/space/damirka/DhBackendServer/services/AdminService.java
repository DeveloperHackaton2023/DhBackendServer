package space.damirka.DhBackendServer.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import space.damirka.DhBackendServer.entities.AdminEntity;
import space.damirka.DhBackendServer.entities.OSIEntity;
import space.damirka.DhBackendServer.repositories.*;

import java.rmi.NoSuchObjectException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;
    private final HouseRepository houseRepository;
    private final OsiRepository osiRepository;
    private final AdminRepository adminRepository;

    private final StatusRepository statusRepository;
    private final TicketRepository ticketRepository;


    public OSIEntity getOsi(String iin) throws NoSuchObjectException {
        AdminEntity admin = adminRepository.findOneByIin(iin);

        if(Objects.isNull(admin))
            throw new NoSuchObjectException("Can't find admin with your iin");

        OSIEntity osi = osiRepository.findOneByAdmin(admin);

        return osi;
    }
}
