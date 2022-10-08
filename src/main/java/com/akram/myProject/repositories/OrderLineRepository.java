package com.akram.myProject.repositories;

import com.akram.myProject.entities.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepository extends JpaRepository<OrderLine,Long> {
    
}
