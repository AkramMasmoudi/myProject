package com.akram.myProject.repositories;

import com.akram.myProject.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
    
}
