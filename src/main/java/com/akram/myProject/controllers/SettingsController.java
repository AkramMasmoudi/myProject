package com.akram.myProject.controllers;

import com.akram.myProject.entities.Article;
import com.akram.myProject.entities.Settings;
import com.akram.myProject.entities.User;
import com.akram.myProject.services.SettingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @ResponseBody
    public String findLanguageByUserId(){

        try{
            return settingsService.findLanguageByUserId();
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }

    }
}
