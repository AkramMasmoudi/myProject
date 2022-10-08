package com.akram.myProject.repositories;

import com.akram.myProject.entities.Coefficient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoefficientRepository extends JpaRepository<Coefficient,Long> {
    
}
