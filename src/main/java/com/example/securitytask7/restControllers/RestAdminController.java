package com.example.securitytask7.restControllers;
import com.example.securitytask7.models.Person;
import com.example.securitytask7.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin/rest")
public class RestAdminController {

    private final PeopleRepository peopleRepository;

    @Autowired
    PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public RestAdminController(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;

    }

    @GetMapping("/users")
    public List<Person> getAllUsers() {
        return peopleRepository.findAll();
    }

    @PostMapping("/users")
    public Person addUser(@Valid @RequestBody Person person) {
        return peopleRepository.save(person);
    }

    @PutMapping("/users/{id}")
    public Person updateUser(@PathVariable("id") long id, @Valid @RequestBody Person updatedPerson) {
        Person person = peopleRepository.findById((int) id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user id: " + id));
        person.setUsername(updatedPerson.getUsername());
        person.setPassword(updatedPerson.getPassword());
        person.setYearOfBirth(updatedPerson.getYearOfBirth());
        return peopleRepository.save(person);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
        Person person = peopleRepository.findById((int) id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user id: " + id));
        peopleRepository.delete(person);
        return ResponseEntity.ok().build();
    }
}
