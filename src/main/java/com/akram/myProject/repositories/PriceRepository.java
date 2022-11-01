package com.akram.myProject.repositories;

import com.akram.myProject.entities.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price,Long> {

}
