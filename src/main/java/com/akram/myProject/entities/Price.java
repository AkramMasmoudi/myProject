package com.akram.myProject.entities;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "Price")
public class Price implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true,nullable = false,updatable = false)
    private Long priceId;
    @ManyToOne()
    @JoinColumn(name = "unitId")
    private Unit unitId;
    @Column(nullable = false)
    @Value("${my.default.price:0}")
    private double price;
    @ManyToOne()
    @JoinColumn(name = "articleId")
    private Article priceArticleId;
}
