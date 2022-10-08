package com.akram.myProject.repositories;

import com.akram.myProject.entities.Check;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckRepository extends JpaRepository<Check,Long> {
    
}
