package com.akram.myProject.repositories;

import com.akram.myProject.entities.Quantities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface QuantityRepository extends JpaRepository<Quantities,Long> {
    @Query("delete from Quantities q where q.quantityArticleId.articleId = ?1")
    void deleteAllByQuantityArticleId(Long quantityId);
}
