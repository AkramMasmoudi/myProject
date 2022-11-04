package com.akram.myProject.repositories;

import com.akram.myProject.entities.Article;
import com.akram.myProject.entities.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderLineRepository extends JpaRepository<OrderLine,Long> {
    @Query(" select count(ol) from OrderLine ol where ol.orderLineArticleId.articleId = ?1")
    long countAllByOrderLineArticleId(Long articleId);
}
