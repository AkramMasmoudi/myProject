package com.akram.myProject.repositories;

import com.akram.myProject.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person,Long> {
    List<Person> findByPersonType(String personType);

    void deletePersonByPersonId(Long personId);
}
