package com.akram.myProject.entities;

import com.akram.myProject.globalVariables.PersonType;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "Person")
public class Person implements Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true,nullable = false,updatable = false)
    private Long personId;
    @Column(nullable = false)
    private String personFirstName;
    @Column(nullable = false)
    private String personLastName;
    @Column
    @Setter(value=AccessLevel.NONE)
    private String personType;
    @Column
    private String personTel;
    @Column
    private String personTel2;
    @Column
    private String personAddress;
    @Column
    private String personEmail;
    @Column
    @OneToMany(mappedBy = "orderPersonId",fetch = FetchType.LAZY)
    private List<Order> lstOrders;

    public void setPersonType(String personType) {
        if(PersonType.supplier.equalsIgnoreCase(personType) || PersonType.client.equalsIgnoreCase(personType))
            this.personType = personType.toUpperCase();
        else {
            System.err.println("error in setPersonType : personType value ("+personType+") is not valid");
            this.personType = PersonType.client;
        }
    }
}
