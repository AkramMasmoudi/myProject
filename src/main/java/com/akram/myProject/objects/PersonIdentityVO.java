package com.akram.myProject.objects;

import com.akram.myProject.entities.Person;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PersonIdentityVO implements Serializable {

    private String personFirstName;
    private String personLastName;
    private Integer personCivility;
    public PersonIdentityVO(Person person) {
        this.personFirstName = person.getPersonFirstName();
        this.personLastName = person.getPersonLastName();
        this.personCivility = person.getPersonCivility();
    }
}
