package com.example.securitytask7.controllers;

import com.example.securitytask7.repositories.PeopleRepository;
import com.example.securitytask7.services.AdminService;
import com.example.securitytask7.services.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {
    private final AdminService adminService;
    private final PeopleRepository peopleRepository;
    private final PersonDetailsService personDetailsService;


    @Autowired
    public UserController(AdminService adminService, PeopleRepository peopleRepository, PersonDetailsService personDetailsService) {
        this.adminService = adminService;
        this.peopleRepository = peopleRepository;
        this.personDetailsService = personDetailsService;
    }
    @GetMapping("/hello")
    public String sayHello(Model model) {
        model.addAttribute("message", "hello");
        return "hello";
    }

    @GetMapping("/")
    public String getUserPage(Model model, Principal principal) {
        UserDetails userDetails = personDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("userDetails", userDetails);
        return "userPage";
    }
}
