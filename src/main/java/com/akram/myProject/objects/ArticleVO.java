package com.akram.myProject.objects;

import com.akram.myProject.entities.*;
import lombok.*;

import javax.persistence.FetchType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static javax.persistence.FetchType.LAZY;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ArticleVO implements Serializable {

    private Long articleId;
    private String articleName;
    private String articleReference;

    private CategoryVO articleCategory;
    private List<CoefficientVO> lstCoefficient;
    private List<QuantitiesVO> lstQuantity;
    private List<PriceVO> lstPrices;
    private List<OrderLineVO> lstOrderLines;
    private Boolean removed = false;
    public ArticleVO(Article article, FetchType fetchType){
        this.articleId = article.getArticleId();
        this.articleName = article.getArticleName();
        this.articleReference = article.getArticleReference();
        this.removed = article.getRemoved() == null ? false : article.getRemoved();
        this.articleCategory = new CategoryVO(article.getArticleCategoryId(),LAZY);
        if(fetchType.equals(LAZY)){
            this.lstCoefficient = new ArrayList<>();
            this.lstQuantity = new ArrayList<>();
            this.lstPrices = new ArrayList<>();
            this.lstOrderLines = new ArrayList<>();
        }else{
            this.lstCoefficient = article.getLstCoefficient().stream().map((Coefficient coefficient) -> {
                return new CoefficientVO(coefficient, LAZY);
            }).collect(Collectors.toList());
            this.lstQuantity = article.getLstQuantity().stream().map((Quantities quantity) -> {
                return new QuantitiesVO(quantity, LAZY);
            }).collect(Collectors.toList());
            this.lstPrices = article.getLstPrices().stream().map((Price price) -> {
                return new PriceVO(price, LAZY);
            }).collect(Collectors.toList());
            this.lstOrderLines = article.getLstOrderLines().stream().map((OrderLine orderLine) -> {
                return new OrderLineVO(orderLine, LAZY);
            }).collect(Collectors.toList());
        }
    }
}
