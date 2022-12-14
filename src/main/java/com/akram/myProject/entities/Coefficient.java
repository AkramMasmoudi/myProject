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
@Table(name = "Coefficient")
public class Coefficient implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long coefficientId;
    @ManyToOne()
    @JoinColumn(name = "unitId")
    private Unit unitId;
    @Column(nullable = false)
    @Value("${my.default.coefficient:1}")
    private double coefficient;
    @ManyToOne()
    @JoinColumn(name = "articleId")
    private Article coefficientArticleId;
}
