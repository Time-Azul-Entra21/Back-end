package com.projeto.academia.Academia;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {
	
	public void addCorsMapping(CorsRegistry registry) {
		//implementando a liberação cors
		registry.addMapping("/**")
		.allowedOrigins("*") //origens permitidas, nesse caso, todas
		        .allowedMethods("GET","POST","PUT", "DELETE");  //requisões permitidas
		
	}

}
