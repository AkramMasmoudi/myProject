package com.akram.myProject.controllers;

import com.akram.myProject.entities.Article;
import com.akram.myProject.entities.Category;
import com.akram.myProject.entities.Unit;
import com.akram.myProject.services.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping("/all")
    public ResponseEntity<List<Article>> findAllArticles(){

        try{
            List<Article> articles = articleService.findAllArticles();
            return new ResponseEntity<List<Article>>(articles, OK);
        }catch (Exception e){
            return new ResponseEntity<List<Article>>(new ArrayList<Article>(), EXPECTATION_FAILED);
        }

    }
    @GetMapping("/categories")
    public ResponseEntity<List<Category>> findAllCategories(){

        try{
            List<Category> categories = articleService.findAllCategories();
            return new ResponseEntity<List<Category>>(categories, OK);
        }catch (Exception e){
            return new ResponseEntity<List<Category>>(new ArrayList<Category>(), EXPECTATION_FAILED);
        }

    }

    @GetMapping("/units")
    public ResponseEntity<List<Unit>> findAllUnits(){

        try{
            List<Unit> units = articleService.findAllUnits();
            return new ResponseEntity<List<Unit>>(units, OK);
        }catch (Exception e){
            return new ResponseEntity<List<Unit>>(new ArrayList<Unit>(), EXPECTATION_FAILED);
        }

    }
}
