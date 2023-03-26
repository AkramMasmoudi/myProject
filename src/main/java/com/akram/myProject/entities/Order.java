package com.akram.myProject.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "Orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true,nullable = false,updatable = false)
    private Long orderId;
    @Column
    private LocalDate orderDate;
    @ManyToOne()
    @JoinColumn(name = "personId")
    private Person orderPersonId;
    @Column
    private double orderCash;
    @Column
    @OneToMany(mappedBy = "checkOrderId",fetch = LAZY)
    private List<Check> lstOrderChecks;
    @Column
    @OneToMany(mappedBy = "orderLineOrderId",fetch = LAZY)
    private List<OrderLine> lstOrderLines;


}
