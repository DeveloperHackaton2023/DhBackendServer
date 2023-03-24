package space.damirka.DhBackendServer.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tests/")
public class TestController {

    @GetMapping("test/")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok("test");
    }
}
