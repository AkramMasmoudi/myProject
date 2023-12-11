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
    @PostMapping ("/addOrUpdate")
    public ResponseEntity<ResponseObject<PersonVO>> saveClient(@RequestBody PersonVO client){
        ResponseObject<PersonVO> response = new ResponseObject<>();
        try{
            if(client != null){
                PersonVO person = personService.saveClient(client);
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

    @DeleteMapping("/delete/{personId}")
    public ResponseEntity<ResponseObject<String>> delete(@PathVariable Long personId){
        ResponseObject<String> response = new ResponseObject<>();
        try{
            if(personId != null){
                boolean deleted = personService.deletePerson(personId);
                response.setSingleData(Boolean.toString(deleted));
                return new ResponseEntity<>(response, OK);
            }else{
                response.setErrorMessage("personId null when trying to delete a person");
                return new ResponseEntity<>(response,INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e){
            response.setErrorMessage(e.getMessage());
            return new ResponseEntity<>(response,INTERNAL_SERVER_ERROR);
        }
    }
}
