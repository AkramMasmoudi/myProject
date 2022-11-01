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
@Table(name = "Unit")
public class Unit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true,nullable = false,updatable = false)
    private Long unitId;
    @Column(unique = true,nullable = false)
    private String shortName;
    @Column(unique = true,nullable = false)
    private String name;
    @ToString.Exclude
    @Column
    @OneToMany(mappedBy = "unitId",fetch = FetchType.LAZY)
    private List<Quantities> lstQuantities;
    @ToString.Exclude
    @Column
    @OneToMany(mappedBy = "unitId",fetch = FetchType.LAZY)
    private List<Coefficient> lstCoefficients;
}
