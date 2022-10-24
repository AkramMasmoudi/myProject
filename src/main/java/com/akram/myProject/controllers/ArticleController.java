package com.akram.myProject.controllers;

import com.akram.myProject.entities.*;
import com.akram.myProject.services.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
@CrossOrigin("*")
@Slf4j
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping("/all")
    public ResponseEntity<List<Article>> findAllArticles(){

        try{
            List<Article> articles = articleService.findAllArticles();
            return new ResponseEntity<>(articles, OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ArrayList<>(), EXPECTATION_FAILED);
        }

    }
    @GetMapping("/categories")
    public ResponseEntity<List<Category>> findAllCategories(){

        try{
            List<Category> categories = articleService.findAllCategories();
            return new ResponseEntity<>(categories, OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ArrayList<>(), EXPECTATION_FAILED);
        }

    }

    @GetMapping("/units")
    public ResponseEntity<List<Unit>> findAllUnits(){

        try{
            List<Unit> units = articleService.findAllUnits();
            return new ResponseEntity<>(units, OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ArrayList<>(), EXPECTATION_FAILED);
        }

    }
    @PostMapping("/article")
    public Article saveArticle(@RequestBody Article article){
        try {
           log.info(article.toString());
            final String name = article.getArticleName();
            final String ref = article.getArticleReference() == null ? "" : article.getArticleReference();
            final Long articleId = article.getArticleId();
            if(name == null || name.isBlank()){
                throw new NullPointerException();
            }
            Optional<Category> category = Optional.of(article.getArticleCategoryId());
            if(category.isPresent() && category.get().getCategoryId() != null)
                category = articleService.findCategoryByCategoryId(category.get().getCategoryId());
            Article articleToSave;
            if(articleId != null){ //modify
                if(category.isPresent()){
                    articleToSave = new Article(articleId ,name,category.get(),ref);
                }else{
                    articleToSave = new Article(articleId ,name,null,ref);
                }
            }else{ //add
                if(category.isPresent()){
                    articleToSave = new Article(name,category.get(),ref);
                }else{
                    articleToSave = new Article(name,null,ref);
                }
            }
            return articleService.saveArticle(articleToSave);
        }catch (Exception e){
            return null;
        }
    }

}
