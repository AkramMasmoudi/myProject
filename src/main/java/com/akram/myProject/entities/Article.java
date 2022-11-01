package com.akram.myProject.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "Article")
public class Article implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true,nullable = false,updatable = false)
    private Long articleId;
    @Column(nullable = false)
    private String articleName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId")
    private Category articleCategoryId;
    @ToString.Exclude
    @Column
    @OneToMany(mappedBy = "coefficientArticleId",fetch = FetchType.LAZY)
    private List<Coefficient> lstCoefficient;
    @ToString.Exclude
    @Column
    @OneToMany(mappedBy = "quantityArticleId",fetch = FetchType.LAZY)
    private List<Quantities> lstQuantity;
    @ToString.Exclude
    @Column
    @OneToMany(mappedBy = "priceArticleId",fetch = FetchType.LAZY)
    private List<Price> lstPrices;
    @Column
    private String articleReference;
    @ToString.Exclude
    @Column
    @OneToMany(mappedBy = "orderLineArticleId",fetch = FetchType.LAZY)
    private List<OrderLine> lstOrderLines;

    public Article(String articleName, Category articleCategoryId, String articleReference) {
        this.articleName = articleName;
        this.articleCategoryId = articleCategoryId;
        this.articleReference = articleReference;
    }

    public Article(Long articleId, String articleName, Category articleCategoryId, String articleReference) {
        this.articleId = articleId;
        this.articleName = articleName;
        this.articleCategoryId = articleCategoryId;
        this.articleReference = articleReference;
    }
}
