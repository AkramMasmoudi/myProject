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
    @Column
    private String articleName;
    @Column
    private Double availableQte;
    @ManyToOne()
    @JoinColumn(name = "categoryId")
    private Category articleCategoryId;
    @Column
    private double articlePrice;
    @Column
    private double purchasingPrice;
    @ManyToOne()
    @JoinColumn(name = "unitId")
    private Unit articleDefaultUnit;
    @Column
    @OneToMany(mappedBy = "coefficientArticleId",fetch = FetchType.LAZY)
    private List<Coefficient> lstCoefficient;
    @Column
    private String articleReference;
    @Column
    @OneToMany(mappedBy = "orderLineArticleId",fetch = FetchType.LAZY)
    private List<OrderLine> lstOrderLines;
}
