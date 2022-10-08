package com.akram.myProject.repositories;

import com.akram.myProject.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    
}
