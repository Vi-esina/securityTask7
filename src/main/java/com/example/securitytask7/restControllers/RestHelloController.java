package com.example.securitytask7.restControllers;

import com.example.securitytask7.repositories.PeopleRepository;
import com.example.securitytask7.services.AdminService;
import com.example.securitytask7.services.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


/**
 * @author Neil Alishev
 */
@RestController
@RequestMapping("/user/rest")
public class RestHelloController {
    private final AdminService adminService;
    private final PeopleRepository peopleRepository;
    private final PersonDetailsService personDetailsService;


    @Autowired
    public RestHelloController(AdminService adminService, PeopleRepository peopleRepository, PersonDetailsService personDetailsService) {
        this.adminService = adminService;
        this.peopleRepository = peopleRepository;
        this.personDetailsService = personDetailsService;
    }

    @GetMapping("/hello")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("hello");
    }

    @GetMapping(value = "/")
    public ResponseEntity<UserDetails> getUserPage2(Principal principal) {
        UserDetails userDetails = personDetailsService.loadUserByUsername(principal.getName());
        return ResponseEntity.ok(userDetails);
    }

}
