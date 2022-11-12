package com.akram.myProject.repositories;

import com.akram.myProject.entities.Quantities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface QuantityRepository extends JpaRepository<Quantities,Long> {
    @Modifying
    @Query("delete from Quantities q where q.quantityArticleId.articleId = ?1")
    @Transactional
    void deleteAllByQuantityArticleId(Long quantityId);
}
