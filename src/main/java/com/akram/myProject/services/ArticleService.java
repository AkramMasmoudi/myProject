package com.akram.myProject.services;

import com.akram.myProject.entities.Article;
import com.akram.myProject.entities.Category;
import com.akram.myProject.entities.Unit;
import com.akram.myProject.objects.ArticleVO;
import com.akram.myProject.objects.CategoryVO;
import com.akram.myProject.objects.UnitVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.FetchType;
import java.util.List;
import java.util.Optional;

public interface ArticleService {
    Article saveArticle(Article article);
    List<ArticleVO> findAllArticles(FetchType fetchType);
    List<CategoryVO> findAllCategories(FetchType fetchType);
    List<UnitVO> findAllUnits(FetchType fetchType);
    Optional<Category> findCategoryByCategoryId(Long categoryId);
    Optional<Article> findArticleById(Long id);
    ArticleVO findArticleById(Long id, FetchType fetchType);
    @Transactional
    boolean deleteArticle(Long id);
    void safeRemoveArticle(Long id);
    void addUnit(Unit unit);
    void addCategory(Category category);
}
