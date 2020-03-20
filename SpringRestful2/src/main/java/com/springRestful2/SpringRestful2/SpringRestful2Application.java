package com.springRestful2.SpringRestful2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Locale;

@SpringBootApplication
public class SpringRestful2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringRestful2Application.class, args);
	}
	@Bean
	public LocaleResolver localeResolver(){
		//SessionLocaleResolver localeResolver=new SessionLocaleResolver();
		AcceptHeaderLocaleResolver localeResolver=new AcceptHeaderLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}

}
