package com.akram.myProject.servicesImpl;

import com.akram.myProject.services.TranslationService;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;


@Service
public class TranslationServiceImpl implements TranslationService {
    private final ResourceBundleMessageSource messageSource;

    public TranslationServiceImpl(ResourceBundleMessageSource messageSource) {
        this.messageSource = messageSource;
    }
    @Override
    public String getTranslation(String code){
        if(code == null || code.isBlank() || code.isEmpty())
            return code;
        return toLocale(code);
    }

    private String toLocale(String code){
        try {
            Locale locale = LocaleContextHolder.getLocale();
            return messageSource.getMessage(code,null,locale);
        }catch (Exception e){
            e.printStackTrace();
            return code;
        }
    }

}
