package com.akram.myProject.servicesImpl;

import com.akram.myProject.entities.Person;
import com.akram.myProject.globalVariables.PersonType;
import com.akram.myProject.objects.PersonVO;
import com.akram.myProject.repositories.PersonRepository;
import com.akram.myProject.services.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static javax.persistence.FetchType.LAZY;
@Slf4j
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public List<PersonVO> findByPersonType(String personType) {
        if(PersonType.CLIENT.equals(personType) || PersonType.SUPPLIER.equals(personType)){
            List<Person> persons = this.personRepository.findByPersonType(personType);
            return persons.stream().map(p -> new PersonVO(p,LAZY)).collect(Collectors.toList());
        }else{
            throw new NullPointerException("personType ("+personType+") is not valid");
        }
    }

    @Override
    public PersonVO saveClient(PersonVO personVO) {
        return savePerson(personVO,PersonType.CLIENT);
    }

    @Override
    public PersonVO saveSupplier(PersonVO personVO) {
        return savePerson(personVO,PersonType.SUPPLIER);
    }

    private PersonVO savePerson(PersonVO personVO,String personType){
        personVO.setPersonType(personType);
        Person person = new Person(personVO);
        Person personSaved = this.personRepository.save(person);
        return new PersonVO(personSaved, LAZY);
    }
    @Override
    @Transactional
    public boolean deletePerson(Long personId) {
        try {
            this.personRepository.deletePersonByPersonId(personId);
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
        return true;
    }
}
