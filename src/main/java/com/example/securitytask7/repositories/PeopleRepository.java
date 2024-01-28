package com.example.securitytask7.repositories;

import com.example.securitytask7.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * @author Neil Alishev
 */
@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
   Optional<Person> findByUsername(String username);

}
