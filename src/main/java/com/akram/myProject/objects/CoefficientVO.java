package com.akram.myProject.objects;

import com.akram.myProject.entities.Article;
import com.akram.myProject.entities.Coefficient;
import lombok.*;

import javax.persistence.FetchType;
import java.io.Serializable;

import static javax.persistence.FetchType.LAZY;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CoefficientVO implements Serializable {
    private Long coefficientId;
    private UnitVO unit;
    private double coefficient;
    private ArticleVO coefficientArticle;

    public CoefficientVO(Coefficient coefficient, FetchType fetchType){
        this.coefficientId = coefficient.getCoefficientId();
        this.coefficient = coefficient.getCoefficient();
        this.unit = new UnitVO(coefficient.getUnitId(),LAZY);
        this.coefficientArticle = new ArticleVO(coefficient.getCoefficientArticleId(),LAZY);
    }
}
