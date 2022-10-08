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
@Table(name = "Category")
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true,nullable = false,updatable = false)
    private Long categoryId;
    @Column
    private String categoryName;
    @Column
    @OneToMany(mappedBy = "articleCategoryId",fetch = FetchType.LAZY)
    private List<Article> lstArticles;
}
