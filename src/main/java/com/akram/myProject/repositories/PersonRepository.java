package com.akram.myProject.repositories;

import com.akram.myProject.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Long> {
    
}
