package space.damirka.DhBackendServer.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import space.damirka.DhBackendServer.dtos.RespondUserInfoDto;
import space.damirka.DhBackendServer.dtos.UserAddTicketHouseDto;
import space.damirka.DhBackendServer.dtos.UserInfoDto;
import space.damirka.DhBackendServer.services.UserService;

import java.security.Principal;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user/")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @PreAuthorize(value = "hasRole('ROLE_USER')")
    @PostMapping("get/info/")
    public ResponseEntity<?> getUserInfo(@RequestBody UserInfoDto userDto) {
        try {
            return ResponseEntity.ok(userService.getInfo(userDto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("get/ticket/{id}")
    public ResponseEntity<?> getTicket(Long id) {
        try {
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("add/ticket/")
    public ResponseEntity<?> addTicket(@RequestBody UserAddTicketHouseDto houseDto) {
        try {
            userService.addTicket(houseDto);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("get/")
    public ResponseEntity<?> getUser(Principal principal) {
        try {
            if(principal instanceof UsernamePasswordAuthenticationToken) {
                RespondUserInfoDto respond = userService.getUserByIin(principal.getName());

                respond.setRoles(((UsernamePasswordAuthenticationToken) principal)
                        .getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(", ")));

                return ResponseEntity.ok(respond);
            }
            return ResponseEntity.badRequest().body("You are not authorized");

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("get/houses/")
    public ResponseEntity<?> getHouses(Principal principal) {
        try {
            if(principal instanceof UsernamePasswordAuthenticationToken) {
                return ResponseEntity.ok(userService.getHousesOfUser(principal.getName()));
            }
            return ResponseEntity.badRequest().body("You are not authorized");

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
