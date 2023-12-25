package com.akram.myProject.entities;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;

import static javax.persistence.GenerationType.IDENTITY;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "Price")
public class Price implements Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true,nullable = false,updatable = false)
    private Long priceId;
    @ManyToOne()
    @JoinColumn(name = "unitId")
    private Unit unitId;
    @Column(nullable = false)
    @Value("${my.default.price:0}")
    private double value;
    @ManyToOne()
    @JoinColumn(name = "articleId")
    private Article priceArticleId;
    @Column(nullable = false)
    @Setter(value=AccessLevel.NONE)
    private char priceType;

    public void setPriceType(char priceType) {
        char priceTypeLowerCase = Character.toLowerCase(priceType);
        if(Arrays.asList('s','p').contains(priceTypeLowerCase)){
            this.priceType = priceTypeLowerCase;
        }else{
            throw new NullPointerException("priceType ("+ priceType +") is not valid : the type must be s or p");
        }
    }
}
