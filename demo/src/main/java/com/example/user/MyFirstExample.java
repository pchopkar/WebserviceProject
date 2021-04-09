package com.example.user;


import java.util.Locale;

import org.apache.tomcat.util.descriptor.LocalResolver;
import org.hibernate.validator.spi.messageinterpolation.LocaleResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication
public class MyFirstExample {
 public static void main(String[] args) {
	 SpringApplication.run(MyFirstExample.class, args);
}
 
 //33 Internationalization
 //1) Create localresolver - to create default locale
 @Bean
 public SessionLocaleResolver localresolver()
 {
	 SessionLocaleResolver localresolver = new SessionLocaleResolver();
	 localresolver.setDefaultLocale(Locale.US);
	return localresolver;
 }
//33 Internationalization
 //2)create properties for each language
 // 3)Then create the Bean ResourceBundleMessageSource to fetch data from properties file
	/*
	 * @Bean public ResourceBundleMessageSource bundlemessage() {
	 * ResourceBundleMessageSource resource = new ResourceBundleMessageSource();
	 * resource.setBasename("message");//To read from message...properties file
	 * return resource;
	 * 
	 * }
	 */
 //34 Internationalization 2
 //2) add AcceptHeaderLocaleResolver
 @Bean
 public AcceptHeaderLocaleResolver acceptheaderlocalresolver()
 {
	 AcceptHeaderLocaleResolver ac = new AcceptHeaderLocaleResolver();
	 ac.setDefaultLocale(Locale.US);
	return ac;	 
 }
 //34 Internationalization 2
 //3) remove ResourceBundleMessageSource method
 // and add on application.properties as : spring.messages.basename=message
 
 
}
