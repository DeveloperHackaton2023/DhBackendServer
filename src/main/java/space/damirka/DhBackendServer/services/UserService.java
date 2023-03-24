package space.damirka.DhBackendServer.services;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import space.damirka.DhBackendServer.Dtos.CreateHouseDto;
import space.damirka.DhBackendServer.Dtos.CreateUserDto;
import space.damirka.DhBackendServer.Dtos.UserInfoDto;
import space.damirka.DhBackendServer.entities.AdminEntity;
import space.damirka.DhBackendServer.entities.OSIEntity;
import space.damirka.DhBackendServer.entities.UserEntity;
import space.damirka.DhBackendServer.entities.UserHouseEntity;
import space.damirka.DhBackendServer.repositories.AdminRepository;
import space.damirka.DhBackendServer.repositories.HouseRepository;
import space.damirka.DhBackendServer.repositories.OsiRepository;
import space.damirka.DhBackendServer.repositories.UserRepository;

import java.rmi.NoSuchObjectException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final HouseRepository houseRepository;
    private final OsiRepository osiRepository;
    private final AdminRepository adminRepository;

    @Autowired
    public UserService(UserRepository userRepository, HouseRepository houseRepository, OsiRepository osiRepository,
                       AdminRepository adminRepository) {
        this.userRepository = userRepository;
        this.houseRepository = houseRepository;
        this.osiRepository = osiRepository;
        this.adminRepository = adminRepository;
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

        UserEntity user = userRepository.findOneByIin(userDto.getIin());

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

        UserEntity user = userRepository.findOneByIin(userDto.getIin());
        if(Objects.isNull(user))
            throw new NoSuchObjectException("Can't find user with your iin");
        return user;
    }
}
