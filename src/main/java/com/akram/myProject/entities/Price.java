package com.akram.myProject.entities;

import com.akram.myProject.globalVariables.UserRoles;
import lombok.*;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.xml.datatype.DatatypeConfigurationException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Locale;

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
    @Column(nullable = false,length = 1)
    @Setter(value=AccessLevel.NONE)
    private String priceType;

    public void setPriceType(String priceType) {
        if(priceType!= null && Arrays.asList("s","p").contains(priceType.toLowerCase())){
            this.priceType = priceType.toLowerCase();
        }else{
            throw new NullPointerException("priceType ("+ priceType +") is not valid : the type must be s or p");
        }
    }
}
