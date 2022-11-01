package com.akram.myProject.objects;

import com.akram.myProject.entities.Coefficient;
import com.akram.myProject.entities.Price;
import lombok.*;

import javax.persistence.FetchType;
import java.io.Serializable;

import static javax.persistence.FetchType.LAZY;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PriceVO implements Serializable {
    private Long priceId;
    private UnitVO unit;
    private double price;
    private ArticleVO priceArticle;
    private String priceType;

    public PriceVO(Price price, FetchType fetchType){
        this.priceId = price.getPriceId();
        this.price = price.getPrice();
        this.unit = new UnitVO(price.getUnitId(),LAZY);
        this.priceArticle = new ArticleVO(price.getPriceArticleId(),LAZY);
    }
}
