package space.damirka.DhBackendServer.controllers.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import space.damirka.DhBackendServer.dtos.RespondUserInfoDto;
import space.damirka.DhBackendServer.models.requests.UsersRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import space.damirka.DhBackendServer.services.UserService;
import space.damirka.DhBackendServer.services.auth.AuthUsersService;
import space.damirka.DhBackendServer.models.auth.Users;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/auth/users/")
public class UsersController {

    private final AuthUsersService authUsersService;

    private final UserService userService;

    @Autowired
    public UsersController(AuthUsersService authUsersService, UserService userService) {
        this.authUsersService = authUsersService;
        this.userService = userService;
    }

    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @GetMapping("get/all/")
    public List<Users> getUsers() {
        return authUsersService.GetAllUsers();
    }

    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @PostMapping("add/")
    public Users getUsers(@RequestBody UsersRequest user) {
        return authUsersService.AddUser(user);
    }

}
