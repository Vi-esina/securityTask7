package com.example.securitytask7.restControllers;


import com.example.securitytask7.models.Person;
import com.example.securitytask7.services.PersonDetailsService;
import com.example.securitytask7.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Neil Alishev
 */
@RestController
@RequestMapping("/auth/rest")
public class RestAuthController {

    @Autowired
    private PasswordEncoder passwordEncoder;
    private final RegistrationService registrationService;
private final PersonDetailsService personDetailsService;

    @Autowired
    public RestAuthController(RegistrationService registrationService, PersonDetailsService personDetailsService) {
        this.registrationService = registrationService;
        this.personDetailsService = personDetailsService;
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");

        UserDetails userDetails = personDetailsService.loadUserByUsername(username);

        if (userDetails == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new UsernameNotFoundException("Invalid username or password");
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return ResponseEntity.ok("Login successful");
    }

    @GetMapping("/registration")
    public ResponseEntity<String> registrationPage() {
        return ResponseEntity.ok("Render the registration page here");
    }

    @PostMapping("/registration")
    public ResponseEntity<String> performRegistration(@RequestBody Person person) {
        // Выполните регистрацию пользователя здесь
        registrationService.register(person);
        return ResponseEntity.ok("Registration successful");
    }
}
