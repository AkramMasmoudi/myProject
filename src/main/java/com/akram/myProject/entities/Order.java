package com.akram.myProject.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "Orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    @OneToMany(mappedBy = "checkOrderId",fetch = FetchType.LAZY)
    private List<Check> lstOrderChecks;
    @Column
    @OneToMany(mappedBy = "orderLineOrderId",fetch = FetchType.LAZY)
    private List<OrderLine> lstOrderLines;


}
