package com.akram.myProject.objects;

import com.akram.myProject.entities.Article;
import com.akram.myProject.entities.Category;
import lombok.*;

import javax.persistence.FetchType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static javax.persistence.FetchType.LAZY;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CategoryVO implements Serializable {

    private Long categoryId;
    private String categoryName;
    private List<ArticleVO> lstArticles;

    public CategoryVO(Category category, FetchType fetchType){
        this.categoryId = category.getCategoryId();
        this.categoryName = category.getCategoryName();
        this.lstArticles = new ArrayList<>();
        if(fetchType.equals(LAZY))
            this.lstArticles = new ArrayList<>();
        else
            this.lstArticles = category.getLstArticles().stream().map((Article article) -> {
                return new ArticleVO(article,LAZY);
            }).collect(Collectors.toList());
    }
}
