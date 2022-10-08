package com.akram.myProject.entities;

import com.akram.myProject.globalVariables.UserRoles;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "Users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true,nullable = false,updatable = false)
    private Long userId;
    @Column(unique = true,nullable = false)
    private String userLogin;
    @Column(nullable = false)
    private String userPassword ;
    @Column(nullable = false)
    @Setter(value=AccessLevel.NONE)
    private String userRole;

    public void setUserRole(String userRole) {
        if(UserRoles.admin.equalsIgnoreCase(userRole) || UserRoles.user.equalsIgnoreCase(userRole))
            this.userRole = userRole.toUpperCase();
        else {
            System.err.println("error in setUserRole : userRole value ("+userRole+") is not valid");
            this.userRole = UserRoles.user;
        }
    }
}
