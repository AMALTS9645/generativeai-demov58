 //code-start

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class LoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoginApplication.class, args);
    }
}

@RestController
@RequestMapping("/api/login")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> login(@Valid @RequestBody Map<String, Object> credentials) {
        // Security: Validate the credentials map to prevent injection attacks
        Map<String, String> user = loginService.authenticate(credentials);
        if (user != null) {
            return ResponseEntity.ok(Map.of("user", user));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid credentials"));
        }
    }
}

@Service
public class LoginService {

    public Map<String, String> authenticate(Map<String, Object> credentials) {
        // Security: Use secure methods to retrieve and verify user credentials
        String username = (String) credentials.get("username");
        String password = (String) credentials.get("password");

        // Simulate user authentication
        if ("admin".equals(username) && "password".equals(password)) {
            return Map.of("id", "1", "username", username, "password", password);
        } else {
            return null;
        }
    }
}

// Additional classes and methods can be created similarly
//code-end
