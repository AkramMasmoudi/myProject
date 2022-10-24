package com.akram.myProject.servicesImpl;

import com.akram.myProject.entities.Article;
import com.akram.myProject.entities.Category;
import com.akram.myProject.entities.Unit;
import com.akram.myProject.repositories.ArticleRepository;
import com.akram.myProject.repositories.CategoryRepository;
import com.akram.myProject.repositories.UnitRepository;
import com.akram.myProject.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    UnitRepository unitRepository;

    @Override
    public Article saveArticle(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public List<Article> findAllArticles() {
        return articleRepository.findAll();
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Unit> findAllUnits() { return unitRepository.findAll(); }

    @Override
    public Optional<Category> findCategoryByCategoryId(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }
}
