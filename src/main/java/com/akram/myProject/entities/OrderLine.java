package com.akram.myProject.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class OrderLine implements Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true,nullable = false,updatable = false)
    private Long orderLineId;
    @Column
    private int articleQte;//default unit
    @ManyToOne()
    @JoinColumn(name = "orderId")
    private Order orderLineOrderId;
    @ManyToOne()
    @JoinColumn(name = "articleId")
    private Article orderLineArticleId;
}
