package com.akram.myProject.servicesImpl;

import com.akram.myProject.entities.Article;
import com.akram.myProject.repositories.ArticleRepository;
import com.akram.myProject.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;

public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleRepository articleRepository;

    @Override
    public void saveArticle(Article article) {
        articleRepository.save(article);
    }
}
