package com.akram.myProject.entities;

import com.akram.myProject.globalVariables.PersonType;
import com.akram.myProject.objects.PersonVO;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
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
    @Column(nullable = false)
    @Setter(value=AccessLevel.NONE)
    private Integer personCivility;
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
        if(PersonType.SUPPLIER.equalsIgnoreCase(personType) || PersonType.CLIENT.equalsIgnoreCase(personType))
            this.personType = personType.toUpperCase();
        else {
            System.err.println("error in setPersonType : personType value ("+personType+") is not valid");
            this.personType = PersonType.CLIENT;
        }
    }

    public void setPersonCivility(Integer personCivility) {
        if(personCivility != null &&  personCivility != 1 && personCivility != 0 ){
            this.personCivility = 0;
        }else{
            this.personCivility = personCivility;
        }
    }


    public Person(PersonVO person) {
        this.personId = person.getPersonId();
        this.personFirstName = person.getPersonIdentityVO().getPersonFirstName();
        this.personLastName = person.getPersonIdentityVO().getPersonLastName();
        this.personType = person.getPersonType();
        this.personTel = person.getPersonTel();
        this.personTel2 = person.getPersonTel2();
        this.personAddress = person.getPersonAddress();
        this.personEmail = person.getPersonEmail();
        this.personCivility = person.getPersonIdentityVO().getPersonCivility();
        this.lstOrders = new ArrayList<>();
    }
}
