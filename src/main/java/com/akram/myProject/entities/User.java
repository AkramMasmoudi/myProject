package com.akram.myProject.entities;

import com.akram.myProject.globalVariables.UserRoles;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "Users")
@Slf4j
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
        if(UserRoles.ADMIN.equalsIgnoreCase(userRole) || UserRoles.USER.equalsIgnoreCase(userRole) || UserRoles.WATCHER.equalsIgnoreCase(userRole))
            this.userRole = userRole.toUpperCase();
        else {
            log.error("error in setUserRole : userRole value ("+userRole+") is not valid");
            this.userRole = UserRoles.WATCHER;
        }
    }
}
