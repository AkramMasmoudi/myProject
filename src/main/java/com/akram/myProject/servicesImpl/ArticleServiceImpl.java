package com.akram.myProject.servicesImpl;

import com.akram.myProject.entities.Article;
import com.akram.myProject.entities.Category;
import com.akram.myProject.entities.Unit;
import com.akram.myProject.objects.ArticleVO;
import com.akram.myProject.objects.CategoryVO;
import com.akram.myProject.objects.UnitVO;
import com.akram.myProject.repositories.ArticleRepository;
import com.akram.myProject.repositories.CategoryRepository;
import com.akram.myProject.repositories.UnitRepository;
import com.akram.myProject.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.FetchType;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<ArticleVO> findAllArticles(FetchType fetchType) {
        List<Article> articles = articleRepository.findAll();
        return articles.stream().map(article -> new ArticleVO(article,fetchType)).collect(Collectors.toList());
    }

    @Override
    public List<CategoryVO> findAllCategories(FetchType fetchType) {
        List<Category> categories =  categoryRepository.findAll();
        return categories.stream().map(category -> new CategoryVO(category,fetchType)).collect(Collectors.toList());
    }

    @Override
    public List<UnitVO> findAllUnits(FetchType fetchType) {
        List<Unit> units = unitRepository.findAll();
        return units.stream().map(unit -> new UnitVO(unit,fetchType)).collect(Collectors.toList());
    }

    @Override
    public Optional<Category> findCategoryByCategoryId(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }
    @Override
    public Optional<Article> findArticleById(Long id){
        return articleRepository.findById(id);
    }
}
