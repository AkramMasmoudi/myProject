package com.akram.myProject.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

import static javax.persistence.GenerationType.IDENTITY;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "Checks")
public class Check implements Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true,nullable = false,updatable = false)
    private Long checkId;
    @Column
    private String checkNumber;
    @Column
    private String bank;
    @Column
    private String bankBranch;
    @Column
    private LocalDate checkDate;
    @ManyToOne()
    @JoinColumn(name = "orderId")
    private Order checkOrderId;

}
