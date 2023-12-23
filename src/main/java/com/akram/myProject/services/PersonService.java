package com.akram.myProject.services;

import com.akram.myProject.objects.PersonVO;

import java.util.List;

public interface PersonService {
    public List<PersonVO> findByPersonType(String personType);
    public PersonVO saveClient(PersonVO personVO);

    public PersonVO saveSupplier(PersonVO personVO);

    public boolean deletePerson(Long personId);
}
