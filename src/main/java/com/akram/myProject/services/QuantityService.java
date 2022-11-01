package com.akram.myProject.services;

import com.akram.myProject.entities.Quantities;

import java.util.List;

public interface QuantityService {
    public List<Quantities> saveAllQuantities(List<Quantities> quantities);

}
