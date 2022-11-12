package com.akram.myProject.repositories;

import com.akram.myProject.entities.Coefficient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CoefficientRepository extends JpaRepository<Coefficient,Long> {
    @Modifying
    @Query("delete from Coefficient c where c.coefficientArticleId.articleId = ?1")
    @Transactional
    void deleteAllByCoefficientArticleId(Long coefficientId);
}
