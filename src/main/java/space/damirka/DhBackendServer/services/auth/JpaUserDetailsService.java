package space.damirka.DhBackendServer.services.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import space.damirka.DhBackendServer.models.auth.UserSecurity;
import space.damirka.DhBackendServer.repositories.UsersRepository;

@RequiredArgsConstructor
@Repository
public class JpaUserDetailsService implements UserDetailsService {
    private final UsersRepository usersRepository;

    @Override
    public UserSecurity loadUserByUsername(String iin) throws UsernameNotFoundException {
        return usersRepository.findByIin(iin).map(UserSecurity::new).orElseThrow(() -> new UsernameNotFoundException("User Not Found!"));
    }
}
