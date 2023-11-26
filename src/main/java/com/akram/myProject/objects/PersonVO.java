package com.akram.myProject.objects;

import com.akram.myProject.entities.Order;
import com.akram.myProject.entities.Person;
import lombok.*;

import javax.persistence.FetchType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static javax.persistence.FetchType.LAZY;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PersonVO implements Serializable {
    private Long personId;
    private PersonIdentityVO personIdentityVO;
    private String personType;
    private String personTel;
    private String personTel2;
    private String personAddress;
    private String personEmail;
    private List<OrderVO> lstOrders;
    public PersonVO(Person person, FetchType fetchType) {
        this.personId = person.getPersonId();
        this.personType = person.getPersonType();
        this.personTel = person.getPersonTel();
        this.personTel2 = person.getPersonTel2();
        this.personAddress = person.getPersonAddress();
        this.personEmail = person.getPersonEmail();
        this.personIdentityVO = new PersonIdentityVO(person);
        if(fetchType.equals(LAZY)){
            this.lstOrders = new ArrayList<>();
        }else{
            this.lstOrders = person.getLstOrders().stream().map((Order order) -> {
                return new OrderVO(order, LAZY);
            }).collect(Collectors.toList());
        }
    }
}
