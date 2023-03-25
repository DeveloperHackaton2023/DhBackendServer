package space.damirka.DhBackendServer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;
import space.damirka.DhBackendServer.dtos.CreateUserDto;
import space.damirka.DhBackendServer.models.auth.UserSecurity;
import space.damirka.DhBackendServer.models.auth.Users;
import space.damirka.DhBackendServer.services.AdminService;
import space.damirka.DhBackendServer.services.UserService;

import java.security.Principal;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/")
public class AdminController {

    private final UserService userService;

    private final AdminService adminService;

    @Autowired
    public AdminController(UserService userService, AdminService adminService) {
        this.userService = userService;
        this.adminService = adminService;
    }

    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @PostMapping("create/user/")
    public ResponseEntity<?> createUser(@RequestBody CreateUserDto userDto) {
        try {
            userService.createUser(userDto);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @GetMapping("get/osi/")
    public ResponseEntity<?> getOsi(Principal principal) {
        try {
            if(principal instanceof UsernamePasswordAuthenticationToken) {
                String iin = principal.getName();
                return ResponseEntity.ok(adminService.getOsi(iin));
            }
            return ResponseEntity.badRequest().body("You are not authorized");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
