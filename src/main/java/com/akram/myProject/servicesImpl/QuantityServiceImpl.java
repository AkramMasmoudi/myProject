package com.akram.myProject.servicesImpl;

import com.akram.myProject.entities.Quantities;
import com.akram.myProject.repositories.QuantityRepository;
import com.akram.myProject.services.QuantityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class QuantityServiceImpl implements QuantityService {
    @Autowired
    private QuantityRepository quantityRepository;
    @Override
    public List<Quantities> saveAllQuantities(List<Quantities> quantities) {
        return quantityRepository.saveAll(quantities);
    }
}
