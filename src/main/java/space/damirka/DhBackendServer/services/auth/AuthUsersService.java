package space.damirka.DhBackendServer.services.auth;

import space.damirka.DhBackendServer.models.requests.UsersRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import space.damirka.DhBackendServer.repositories.UsersRepository;
import space.damirka.DhBackendServer.models.auth.Users;

import java.util.List;

@Service
public class AuthUsersService {
    private final UsersRepository usersRepository;

    @Autowired
    public AuthUsersService(UsersRepository usersRepository) {
        this.usersRepository =  usersRepository;
    }

    public List<Users> GetAllUsers() {
        return usersRepository.findAll();
    }

    public Users AddUser(UsersRequest user) {
        Users newUser = new Users();
        newUser.setIin(user.getIin());
        newUser.setFullname(user.getFullname());
        newUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        newUser.setRoles(user.getRoles());
        return usersRepository.save(newUser);
    }
}
