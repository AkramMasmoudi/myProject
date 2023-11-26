package com.akram.myProject.controllers;

import com.akram.myProject.globalVariables.PersonType;
import com.akram.myProject.objects.PersonVO;
import com.akram.myProject.services.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/person/client")
@RequiredArgsConstructor
@CrossOrigin("*")
@Slf4j
public class ClientController {
    private final PersonService personService;
    @GetMapping("/all")
    public ResponseEntity<List<PersonVO>> findAllClients(){
        try{
            List<PersonVO> clients = personService.findByPersonType(PersonType.client);
            return new ResponseEntity<>(clients, OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ArrayList<>(), INTERNAL_SERVER_ERROR);
        }
    }

}
