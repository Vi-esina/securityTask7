package com.example.securitytask7.controllers;

import com.example.securitytask7.models.Person;
import com.example.securitytask7.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final PeopleRepository peopleRepository;

    @Autowired
    PasswordEncoder bCryptPasswordEncoder;


@Autowired
    public AdminController(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        List<Person> users = peopleRepository.findAll();
        model.addAttribute("users", users);
        return "users"; // Название представления users.html
    }

    @PostMapping("/users")
    public String addUser(@Valid @ModelAttribute("person") Person person) {
        peopleRepository.save(person);
        return "redirect:/admin/users"; // Перенаправление на GET /admin/users
    }

    @PutMapping("/users/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid @ModelAttribute("updatedPerson") Person updatedPerson) {
        Person person = peopleRepository.findById((int) id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user id: " + id));
        person.setUsername(updatedPerson.getUsername());
        person.setPassword(updatedPerson.getPassword());
        person.setYearOfBirth(updatedPerson.getYearOfBirth());
        peopleRepository.save(person);
        return "redirect:/admin/users"; // Перенаправление на GET /admin/users
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        Person person = peopleRepository.findById((int) id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user id: " + id));
        peopleRepository.delete(person);
        return "redirect:/admin/users"; // Перенаправление на GET /admin/users
    }
}
