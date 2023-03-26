package com.akram.myProject.entities;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "Article")
public class Article implements Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true,nullable = false,updatable = false)
    private Long articleId;
    @Column(nullable = false)
    private String articleName;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "categoryId")
    private Category articleCategoryId;
    @ToString.Exclude
    @Column
    @OneToMany(mappedBy = "coefficientArticleId",fetch = LAZY)
    private List<Coefficient> lstCoefficient;
    @ToString.Exclude
    @Column
    @OneToMany(mappedBy = "quantityArticleId",fetch = LAZY)
    private List<Quantities> lstQuantity;
    @ToString.Exclude
    @Column
    @OneToMany(mappedBy = "priceArticleId",fetch = LAZY)
    private List<Price> lstPrices;
    @Column
    private String articleReference;
    @ToString.Exclude
    @Column
    @OneToMany(mappedBy = "orderLineArticleId",fetch = LAZY)
    private List<OrderLine> lstOrderLines;
    @Column(columnDefinition = "boolean default false")
    private Boolean removed;

    public Article(String articleName, Category articleCategoryId, String articleReference) {
        this.articleName = articleName;
        this.articleCategoryId = articleCategoryId;
        this.articleReference = articleReference;
        this.removed = false;
    }

    public Article(Long articleId, String articleName, Category articleCategoryId, String articleReference) {
        this.articleId = articleId;
        this.articleName = articleName;
        this.articleCategoryId = articleCategoryId;
        this.articleReference = articleReference;
        this.removed = false;
    }
}
