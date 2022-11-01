package com.akram.myProject.objects;

import com.akram.myProject.entities.Article;
import com.akram.myProject.entities.OrderLine;
import lombok.*;

import javax.persistence.FetchType;
import java.io.Serializable;

import static javax.persistence.FetchType.LAZY;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderLineVO implements Serializable {
    private Long orderLineId;
    private int articleQte;
    private OrderVO orderLineOrder;
    private ArticleVO orderLineArticle;

    public OrderLineVO(OrderLine orderLine, FetchType fetchType) {
        this.orderLineId = orderLine.getOrderLineId();
        this.articleQte = orderLine.getArticleQte();
        this.orderLineOrder = new OrderVO(orderLine.getOrderLineOrderId(),LAZY);
        this.orderLineArticle = new ArticleVO(orderLine.getOrderLineArticleId(),LAZY);
    }
}
