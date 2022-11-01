package com.akram.myProject.objects;

import com.akram.myProject.entities.Price;
import com.akram.myProject.entities.Quantities;
import lombok.*;

import javax.persistence.FetchType;
import java.io.Serializable;

import static javax.persistence.FetchType.LAZY;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class QuantitiesVO implements Serializable {
    private Long quantityId;
    private UnitVO unit;
    private double qte;
    private ArticleVO quantityArticle;

    public QuantitiesVO(Quantities quantity, FetchType fetchType){
        this.quantityId = quantity.getQuantityId();
        this.qte = quantity.getQte();
        this.unit = new UnitVO(quantity.getUnitId(),LAZY);
        this.quantityArticle = new ArticleVO(quantity.getQuantityArticleId(),LAZY);
    }
}
