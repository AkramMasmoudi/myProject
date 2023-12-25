package com.akram.myProject.objects;

import com.akram.myProject.entities.Price;
import lombok.*;

import javax.persistence.FetchType;
import java.io.Serializable;

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
    private char priceType;

    public PriceVO(Price price, FetchType fetchType){
        this.priceId = price.getPriceId();
        this.price = price.getValue();
        this.priceType = price.getPriceType();
        this.unit = new UnitVO(price.getUnitId(),fetchType);
        this.priceArticle = new ArticleVO(price.getPriceArticleId(),fetchType);
    }
}
