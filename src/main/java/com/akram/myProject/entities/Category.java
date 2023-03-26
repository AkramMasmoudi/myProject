package com.akram.myProject.entities;

import lombok.*;

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
@Table(name = "Category")
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true,nullable = false,updatable = false)
    private Long categoryId;
    @Column
    private String categoryName;
    @Column
    @OneToMany(mappedBy = "articleCategoryId",fetch = LAZY)
    private List<Article> lstArticles;

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }
}
