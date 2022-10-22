package com.akram.myProject.services;

import com.akram.myProject.entities.Settings;

public interface SettingsService {
    public void updateLanguageByUserId(String language);

    public String findLanguageByUserId();

}
