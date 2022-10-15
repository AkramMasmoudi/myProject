package com.akram.myProject.controllers;

import com.akram.myProject.entities.Article;
import com.akram.myProject.services.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @PostMapping("/all")
    public ResponseEntity<List<Article>> findAllArticles(){

        try{
            List<Article> articles = articleService.findAllArticles();
            return new ResponseEntity<List<Article>>(articles, OK);
        }catch (Exception e){
            return new ResponseEntity<List<Article>>(new ArrayList<Article>(), UNAUTHORIZED);
        }

    }
}
