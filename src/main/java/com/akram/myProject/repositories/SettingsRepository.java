package com.akram.myProject.repositories;

import com.akram.myProject.entities.Settings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface SettingsRepository extends JpaRepository<Settings,Long> {
    @Modifying
    @Transactional
    @Query("update Settings s set s.language = ?1 where s.user.userId = ?2")
    void updateLanguageByUserId(String language, Long userId);

    @Query("select s.language from Settings s where s.user.userId = ?1")
    String findLanguageByUserId(Long userId);

    Settings findSettingsByUserUserId(Long userId);

}
