package com.akram.myProject;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Arrays;
import java.util.Locale;

import static java.util.Locale.ENGLISH;
import static java.util.Locale.FRENCH;

@SpringBootApplication()
@Configuration
@EnableTransactionManagement
public class MyProjectApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(MyProjectApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder BCryptPasswordEncoder()
	{
		return new BCryptPasswordEncoder();
	}

	/**********************************************************/
	/**************** i18n Configuration **********************/
	@Value("${my.base.name}")
	private String baseName;
	@Value("${my.default.local}")
	private String defaultLocale;

	@Bean(name="msg")
	public ResourceBundleMessageSource messageSource(){
		ResourceBundleMessageSource rs = new ResourceBundleMessageSource();
		rs.setBasename(baseName);
		rs.setDefaultLocale(new Locale(defaultLocale));
		rs.setDefaultEncoding("ISO-8859-1");
		rs.setUseCodeAsDefaultMessage(true);
		return rs;
	}

	@Bean
	public LocaleResolver localResolver() {
		AcceptHeaderLocaleResolver acceptHeaderLocaleResolver = new AcceptHeaderLocaleResolver();
		acceptHeaderLocaleResolver.setDefaultLocale(new Locale(defaultLocale));
		acceptHeaderLocaleResolver.setSupportedLocales(Arrays.asList(FRENCH,ENGLISH));
		return acceptHeaderLocaleResolver;
	}
	/******************************************************/

}
