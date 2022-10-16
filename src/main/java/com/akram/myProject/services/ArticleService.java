package com.akram.myProject.services;

import com.akram.myProject.entities.Article;
import com.akram.myProject.entities.Category;
import com.akram.myProject.entities.Coefficient;
import com.akram.myProject.entities.Unit;

import java.util.List;

public interface ArticleService {
    public void saveArticle(Article article);
    public List<Article> findAllArticles();
    public List<Category> findAllCategories();
    public List<Unit> findAllUnits();
}
