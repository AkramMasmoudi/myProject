package com.akram.myProject.servicesImpl;

import com.akram.myProject.entities.Coefficient;
import com.akram.myProject.repositories.CoefficientRepository;
import com.akram.myProject.services.CoefficientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CoefficientServiceImpl implements CoefficientService {
    @Autowired
    private CoefficientRepository coefficientRepository;

    @Override
    public List<Coefficient> saveAllCoefficient(List<Coefficient> coefficients) {
        if(!coefficients.isEmpty())
            coefficientRepository.deleteAllByCoefficientArticleId(coefficients.get(0).getCoefficientArticleId().getArticleId());
        return coefficientRepository.saveAll(coefficients);
    }
}
