package space.damirka.DhBackendServer.services;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.damirka.DhBackendServer.dtos.CreateHouseDto;
import space.damirka.DhBackendServer.dtos.CreateUserDto;
import space.damirka.DhBackendServer.dtos.UserAddTicketHouseDto;
import space.damirka.DhBackendServer.dtos.UserInfoDto;
import space.damirka.DhBackendServer.entities.*;
import space.damirka.DhBackendServer.repositories.*;

import java.rmi.NoSuchObjectException;
import java.util.*;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final HouseRepository houseRepository;
    private final OsiRepository osiRepository;
    private final AdminRepository adminRepository;

    private final StatusRepository statusRepository;
    private final TicketRepository ticketRepository;

    @Autowired
    public UserService(UserRepository userRepository, HouseRepository houseRepository, OsiRepository osiRepository,
                       AdminRepository adminRepository, StatusRepository statusRepository, TicketRepository ticketRepository) {
        this.userRepository = userRepository;
        this.houseRepository = houseRepository;
        this.osiRepository = osiRepository;
        this.adminRepository = adminRepository;
        this.statusRepository = statusRepository;
        this.ticketRepository = ticketRepository;
    }

    @PostConstruct
    public void initializeTestValues() {
        if(Objects.isNull(adminRepository.findOneByIin("TEST"))) {
            AdminEntity admin = new AdminEntity();
            admin.setIin("TEST");
            admin.setTelephone("TEST");
            admin.setFullname("TEST");

            adminRepository.save(admin);
        }

        if(Objects.isNull(osiRepository.findById(1L).orElse(null))) {
            OSIEntity osi = new OSIEntity();
            osi.setAdmin(adminRepository.findOneByIin("TEST"));
            osi.setTitle("TEST");
            osi.setInfo("TEST");

            osiRepository.save(osi);
        }
    }

    public void createUser(CreateUserDto userDto) throws NoSuchObjectException {
        // check if user exists

        UserEntity user = userRepository.findOneByIinAndTelephone(userDto.getIin(), userDto.getTelephone());

        OSIEntity osi = osiRepository.findById(userDto.getOsiId()).orElse(null);

        if(Objects.isNull(osi))
            throw new NoSuchObjectException("Can't find OSI with your id");

        // create user if not present
        if(Objects.isNull(user)) {
            user = new UserEntity();
            user.setIin(userDto.getIin());
            user.setFullname(userDto.getFullname());
            user.setTelephone(userDto.getTelephone());
            user.setAddress(userDto.getAddress());
            user.setHouses(new LinkedList<>());
            user = userRepository.save(user);
        }

        if(Objects.nonNull(userDto.getHouses())) {
            List<CreateHouseDto> houses = userDto.getHouses();
            List<UserHouseEntity> newHouses = user.getHouses();

            for(CreateHouseDto houseDto : houses) {
                if(user.getHouses().stream().noneMatch(userHouseEntity -> Objects.equals(userHouseEntity.getAddress(), houseDto.getAddress()))) {

                    UserHouseEntity newHouse = new UserHouseEntity();
                    newHouse.setAddress(houseDto.getAddress());
                    newHouse.setFlatNumber(houseDto.getFlatNumber());
                    newHouse.setInfo(houseDto.getInfo());
                    newHouse.setUser(user);

                    osi.getHouses().add(newHouse);

                    newHouse = houseRepository.save(newHouse);
                    newHouses.add(newHouse);
                }
            }

            user.setHouses(newHouses);

            userRepository.save(user);

            osiRepository.save(osi);
        }
    }

    public UserEntity getInfo(UserInfoDto userDto) throws NoSuchObjectException {

        UserEntity user = userRepository.findOneByIinAndTelephone(userDto.getIin(), userDto.getTelephone());
        if(Objects.isNull(user))
            throw new NoSuchObjectException("Can't find user with your iin");
        return user;
    }

    private TicketEntity createTicket(UserAddTicketHouseDto houseDto) {
        TicketEntity ticket = new TicketEntity();
        ticket.setSubject(houseDto.getSubject());
        ticket.setDescription(houseDto.getDescription());

        Date now = new Date();
        ticket.setCreated(now);

        StatusEntity statusEntity = new StatusEntity(now);

        statusEntity = statusRepository.save(statusEntity);

        ticket.setStatuses(Collections.singletonList(statusEntity));

        return ticketRepository.save(ticket);
    }

    public void addTicket(UserAddTicketHouseDto houseDto) {
        UserEntity user = userRepository.findOneByIinAndTelephone(houseDto.getIin(), houseDto.getTelephone());
        user.getHouses().forEach(house -> {
            if(Objects.equals(house.getId(), houseDto.getHouseId())) {

                house.getTickets().add(createTicket(houseDto));

                houseRepository.save(house);
            }
        });
    }
}
