package space.damirka.DhBackendServer.controllers.auth;

import space.damirka.DhBackendServer.models.requests.UsersRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import space.damirka.DhBackendServer.services.auth.AuthUsersService;
import space.damirka.DhBackendServer.models.auth.Users;

import java.util.List;


@RestController
@RequestMapping("/api/v1/users")
public class UsersController {

    private final AuthUsersService authUsersService;

    @Autowired
    public UsersController(AuthUsersService authUsersService) {
        this.authUsersService = authUsersService;
    }

    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @GetMapping("")
    public List<Users> GetUsers() {
        return authUsersService.GetAllUsers();
    }

    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @PostMapping("")
    public Users GetUsers(@RequestBody UsersRequest user) {
        return authUsersService.AddUser(user);
    }

}
