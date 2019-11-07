package com.contact.Rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



//helps to get bean and make the map with session for each login and he will be connect to only one token
//this is for use the bean and read it in @Context
@Configuration
public class RestConfig implements WebMvcConfigurer {

	// bean is singleton for each token and will be in hash map
	@Bean(name = "tokens")
	public Map<String, ClientSession> tokenMap() {
		return new HashMap<>();
	}
	
	   @Override
	    public void addCorsMappings(CorsRegistry registry) {
	        registry.addMapping("/**")
	                .allowedOrigins(
	                        "http://localhost:4200")
	                .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD","OPTION")
	                .allowedHeaders("Access-Control-Allow-Credentials", "HttpHeaders","Content-Type","Accept")
	                .allowCredentials(true);
	    }
	
//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
//     registry.addMapping("/**").allowedOrigins("http://localhost:4200").allowedHeaders("/**");
//	}


//	@Overrides
//	public void addCorsMappings(CorsRegistry registry) {
//	       registry.addMapping("/api/**")
//	           .allowedOrigins("http://localhost:4200")
//	           .allowedMethods("PUT", "DELETE","GET","POST")
//	           .allowCredentials(false).maxAge(3600);
//	   }
	}


	




