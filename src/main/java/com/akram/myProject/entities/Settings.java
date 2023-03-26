package com.akram.myProject.entities;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "Setting")
public class Settings implements Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true,nullable = false,updatable = false)
    private Long settingId;
    @Column(nullable = false)
    @Value("${my.default.language:fr-FR")
    private String language;
    @ManyToOne()
    @JoinColumn(name = "userId")
    private User user;

    public Settings(String language, User user) {
        this.language = language;
        this.user = user;
    }
}
