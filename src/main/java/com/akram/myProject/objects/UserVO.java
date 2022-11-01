package com.akram.myProject.objects;

import com.akram.myProject.entities.OrderLine;
import com.akram.myProject.entities.Settings;
import com.akram.myProject.entities.User;
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
public class UserVO implements Serializable {
    private Long userId;
    private String userLogin;
    private String userPassword ;
    private String userRole;
    private List<SettingsVO> lstSettings;

    public UserVO(User user, FetchType fetchType) {
        this.userId = user.getUserId();
        this.userLogin = user.getUserLogin();
        this.userPassword = getUserPassword();
        this.userRole = user.getUserRole();
        if(fetchType.equals(LAZY)){
            this.lstSettings = new ArrayList<>();
        }else{
            this.lstSettings = user.getLstSettings().stream().map((Settings settings) -> {
                return new SettingsVO(settings, LAZY);
            }).collect(Collectors.toList());
        }
    }
}
