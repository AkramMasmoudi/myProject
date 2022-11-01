package com.akram.myProject.objects;

import com.akram.myProject.entities.Settings;
import lombok.*;

import javax.persistence.FetchType;
import java.io.Serializable;

import static javax.persistence.FetchType.LAZY;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SettingsVO implements Serializable {
    private Long settingId;
    private String language;
    private UserVO user;

    public SettingsVO(Settings settings, FetchType fetchType){
        this.settingId = settings.getSettingId();
        this.language = settings.getLanguage();
        this.user = new UserVO(settings.getUser(),LAZY);
    }
}
