package com.akram.myProject.controllers;

import com.akram.myProject.entities.Article;
import com.akram.myProject.entities.Settings;
import com.akram.myProject.entities.User;
import com.akram.myProject.services.SettingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/settings")
@RequiredArgsConstructor
@CrossOrigin("*")
public class SettingsController {
    private final SettingsService settingsService;

    @PutMapping("/language")
    public void saveLanguage(@RequestBody(required = true) String lang){

        try{
            settingsService.updateLanguageByUserId(lang);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    @GetMapping("/language")
    public ResponseEntity<String> findLanguageByUserId(){

        try{
            String lang = settingsService.findLanguageByUserId();
            return new ResponseEntity<String>(lang, OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<String>("", EXPECTATION_FAILED);
        }

    }
}
