package com.akram.myProject.services;

import com.akram.myProject.entities.Article;

import java.util.List;

public interface ArticleService {
    public void saveArticle(Article article);

    public List<Article> findAllArticles();
}
