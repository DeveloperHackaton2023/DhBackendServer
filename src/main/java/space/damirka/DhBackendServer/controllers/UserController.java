package space.damirka.DhBackendServer.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import space.damirka.DhBackendServer.dtos.UserInfoDto;
import space.damirka.DhBackendServer.services.UserService;

@RestController
@RequestMapping("/user/")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

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
    public ResponseEntity<?> addTicket(@RequestBody UserInfoDto userDto) {
        try {
            return ResponseEntity.ok(userService.getInfo(userDto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
