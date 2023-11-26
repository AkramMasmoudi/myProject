package com.akram.myProject.controllers;

import com.akram.myProject.globalVariables.PersonType;
import com.akram.myProject.objects.PersonVO;
import com.akram.myProject.objects.ResponseObject;
import com.akram.myProject.services.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ResponseObject<PersonVO>> findAllClients(){
        ResponseObject<PersonVO> response = new ResponseObject<>();
        try{
            List<PersonVO> clients = personService.findByPersonType(PersonType.client);
            response.setListData(clients);
            return new ResponseEntity<>(response, OK);
        }catch (Exception e){
            return new ResponseEntity<>(response, INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping ("/add")
    public ResponseEntity<ResponseObject<PersonVO>> saveClient(@RequestBody PersonVO newClient){
        ResponseObject<PersonVO> response = new ResponseObject<>();
        try{
            if(newClient != null){
                PersonVO person = personService.saveClient(newClient);
                response.setSingleData(person);
                return new ResponseEntity<>(response, OK);
            }else{
                response.setErrorMessage("Request body null when trying to add a client");
                return new ResponseEntity<>(response,INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e){
            response.setErrorMessage(e.getMessage());
            return new ResponseEntity<>(response,INTERNAL_SERVER_ERROR);
        }
    }
}
