package com.akram.myProject.repositories;

import com.akram.myProject.entities.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnitRepository extends JpaRepository<Unit,Long> {
    
}
