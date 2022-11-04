package com.akram.myProject.repositories;

import com.akram.myProject.entities.Article;
import com.akram.myProject.entities.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PriceRepository extends JpaRepository<Price,Long> {
    @Query("delete from Price p where p.priceArticleId.articleId = ?1")
    void deleteAllByPriceArticleId(Long ArticleId);
}
