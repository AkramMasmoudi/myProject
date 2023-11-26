package com.akram.myProject.services;

import com.akram.myProject.objects.PersonVO;

import java.util.List;

public interface PersonService {
    public List<PersonVO> findByPersonType(String personType);
}