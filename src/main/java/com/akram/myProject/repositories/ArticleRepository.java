package com.akram.myProject.repositories;

import com.akram.myProject.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article,Long> {
    List<Article> findAllByRemovedIsFalse();
    Optional<Article> findArticleByArticleIdAndRemovedIsFalse(Long articleId);
    @Modifying
    @Query("UPDATE Article a SET a.removed = true where a.articleId = ?1")
    @Transactional
    void safeRemoveArticleById(Long articleId);
}
