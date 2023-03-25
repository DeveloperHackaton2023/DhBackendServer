package space.damirka.DhBackendServer.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tests/")
public class TestController {

    @PreAuthorize(value = "hasRole('ROLE_USER')")
    @GetMapping("test/")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok("test");
    }
}
