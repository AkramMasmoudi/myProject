package com.akram.myProject.repositories;

import com.akram.myProject.entities.Coefficient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CoefficientRepository extends JpaRepository<Coefficient,Long> {
    @Query("delete from Coefficient c where c.coefficientArticleId.articleId = ?1")
    void deleteAllByCoefficientArticleId(Long coefficientId);
}
