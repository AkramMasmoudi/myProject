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
@RequestMapping("/person/supplier")
@RequiredArgsConstructor
@CrossOrigin("*")
@Slf4j
public class SupplierController {
    private final PersonService personService;
    @GetMapping("/all")
    public ResponseEntity<ResponseObject<PersonVO>> findAllSuppliers(){
        ResponseObject<PersonVO> response = new ResponseObject<>();
        try{
            List<PersonVO> suppliers = personService.findByPersonType(PersonType.SUPPLIER);
            response.setListData(suppliers);
            return new ResponseEntity<>(response, OK);
        }catch (Exception e){
            return new ResponseEntity<>(response, INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/addOrUpdate")
    public ResponseEntity<ResponseObject<PersonVO>> saveSupplier(@RequestBody PersonVO supplier){
        ResponseObject<PersonVO> response = new ResponseObject<>();
        try{
            if(supplier != null){
                PersonVO person = personService.saveSupplier(supplier);
                response.setSingleData(person);
                return new ResponseEntity<>(response, OK);
            }else{
                response.setErrorMessage("Request body null when trying to add a SUPPLIER");
                return new ResponseEntity<>(response,INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e){
            response.setErrorMessage(e.getMessage());
            return new ResponseEntity<>(response,INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{supplierId}")
    public ResponseEntity<ResponseObject<String>> deleteSupplier(@PathVariable Long supplierId){
        ResponseObject<String> response = new ResponseObject<>();
        try{
            if(supplierId != null){
                boolean deleted = personService.deletePerson(supplierId);
                response.setSingleData(Boolean.toString(deleted));
                return new ResponseEntity<>(response, OK);
            }else{
                response.setErrorMessage("supplierId null when trying to delete a person");
                return new ResponseEntity<>(response,INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e){
            response.setErrorMessage(e.getMessage());
            return new ResponseEntity<>(response,INTERNAL_SERVER_ERROR);
        }
    }
}
