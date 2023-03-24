package space.damirka.DhBackendServer.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import space.damirka.DhBackendServer.Dtos.CreateUserDto;
import space.damirka.DhBackendServer.Dtos.UserInfoDto;
import space.damirka.DhBackendServer.services.UserService;

@RestController
@RequestMapping("/user/")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("create/")
    public ResponseEntity<?> createUser(@RequestBody CreateUserDto userDto) {
        try {
            userService.createUser(userDto);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("get/info/")
    public ResponseEntity<?> createUser(@RequestBody UserInfoDto userDto) {
        try {
            return ResponseEntity.ok(userService.getInfo(userDto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // TODO:
    //  1. Create User
    //      a. if user exist just add new flat
    //  2. Get all flats
    //  3.
    //  4.

}
