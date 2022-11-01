package com.akram.myProject.repositories;

import com.akram.myProject.entities.Quantities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuantityRepository extends JpaRepository<Quantities,Long> {

}
