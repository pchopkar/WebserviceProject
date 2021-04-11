package com.example.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

//import org.apache.tomcat.util.descriptor.LocalResolver;
import org.hibernate.validator.spi.messageinterpolation.LocaleResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
//38 API Swagger Documentation : 2) add configuration and enableswagger annotation 
@Configuration
@EnableSwagger2
@SpringBootApplication
public class MyFirstExample {
	// 40 API Swagger Documentation : declare all constants you can find on ApiInfo for examples and finaly check the swagger documentation
	// check http://localhost:8080/swagger-ui.html and http://localhost:8080/v2/api-docs
	 public static final Contact DEFAULT_CONTACT = new Contact("Parjanya Chopkar", "pchopkar@vertafore.com", "www.vertafore.com");
	  public static final ApiInfo DEFAULT_API_INFO = new ApiInfo("Awsome API documentation", "Second API Documentation", "1.0", "urn:tos",
	          DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0");
	private static final Set<String> DEFAULT_API_CONSUMER_PRODUCER = new HashSet<String>(Arrays.asList("application/json","application/xml"));
	public static void main(String[] args) {
		SpringApplication.run(MyFirstExample.class, args);
	}

	// 33 Internationalization
	// 1) Create localresolver - to create default locale
	
	  @Bean
	  public SessionLocaleResolver localresolver() { SessionLocaleResolver
	  localresolver = new SessionLocaleResolver();
	  localresolver.setDefaultLocale(Locale.US); return localresolver; }
	 

//33 Internationalization
	// 2)create properties for each language
	// 3)Then create the Bean ResourceBundleMessageSource to fetch data from
	// properties file
	
	
	/*
	 * @Bean public ResourceBundleMessageSource bundlemessage() {
	 * ResourceBundleMessageSource resource = new ResourceBundleMessageSource();
	 * resource.setBasename("message");//To read from message...properties file
	 * return resource;
	 * 
	 * }
	 */
	 
	 
	// 34 Internationalization 2
	// 2) add AcceptHeaderLocaleResolver
	@Bean
	public AcceptHeaderLocaleResolver acceptheaderlocalresolver() {
		AcceptHeaderLocaleResolver ac = new AcceptHeaderLocaleResolver();
		ac.setDefaultLocale(Locale.US);
		return ac;
	}
	// 34 Internationalization 2
	// 3) remove ResourceBundleMessageSource method
	// and add on application.properties as : spring.messages.basename=message
	//38 API Swagger Documentation : 1 )Create Bean of Docket return with DocumentationType.SWAGGER_2
	// 40 API Swagger Documentation : 1) add apiInfo and produces and consumes for more detail documentation edit and declare those constants
	@Bean public Docket api()
	{
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(DEFAULT_API_INFO).produces(DEFAULT_API_CONSUMER_PRODUCER).consumes(DEFAULT_API_CONSUMER_PRODUCER);

	}

}
