package space.damirka.DhBackendServer.services.auth;

import space.damirka.DhBackendServer.models.requests.UsersRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import space.damirka.DhBackendServer.repositories.UsersRepository;
import space.damirka.DhBackendServer.models.auth.Users;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final UsersRepository usersRepository;

    public Optional<Users> AddUser(UsersRequest user) {
        Users newUser = new Users();
        newUser.setIin(user.getIin());
        newUser.setFullname(user.getFullname());
        newUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        newUser.setRoles("ROLE_USER");
        return Optional.of(usersRepository.save(newUser));
    }
}
