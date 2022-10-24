package com.akram.myProject.services;

import com.akram.myProject.entities.Article;
import com.akram.myProject.entities.Category;
import com.akram.myProject.entities.Unit;

import java.util.List;
import java.util.Optional;

public interface ArticleService {
    public Article saveArticle(Article article);
    public List<Article> findAllArticles();
    public List<Category> findAllCategories();
    public List<Unit> findAllUnits();

    public Optional<Category> findCategoryByCategoryId(Long categoryId);
}
