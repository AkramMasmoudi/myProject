package com.akram.myProject.servicesImpl;

import com.akram.myProject.entities.Settings;
import com.akram.myProject.entities.User;
import com.akram.myProject.repositories.SettingsRepository;
import com.akram.myProject.services.SettingsService;
import com.akram.myProject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SettingsServiceImpl implements SettingsService {
    @Autowired
    private SettingsRepository settingsRepository;
    @Autowired
    private UserService userService;

    @Value("${my.default.language:fr-FR")
    private String defaultLanguage;
    @Override
    public void updateLanguageByUserId(String language) {
        User user  = this.userService.findAuthenticatedUser();
        if(user != null){
            Settings settings = findSettingsByUserUserId(user.getUserId());
            if(settings == null){
                settingsRepository.save(new Settings(language,user));
            }else{
                this.settingsRepository.updateLanguageByUserId(language, user.getUserId());
            }
        }
        //settingsRepository.setLanguageByUserId(language,userId);
    }

    @Override
    public String findLanguageByUserId() {
        User user  = this.userService.findAuthenticatedUser();
        String language = defaultLanguage;
        if(user != null){
            language = this.settingsRepository.findLanguageByUserId(user.getUserId());
            if(language != null && language.trim() != "")
                return language;
        }
        return language;
    }

    private Settings findSettingsByUserUserId(Long userId) {
        return this.settingsRepository.findSettingsByUserUserId(userId);
    }
}
