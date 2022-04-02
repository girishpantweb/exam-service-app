package com.onlineexam.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class StaticResourceConfiguration implements WebMvcConfigurer {

	@Autowired
	private Environment environment;

	@Value("${images.folder.res}")
	private String imageFolder;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(imageFolder)
				.addResourceLocations("file:/" + environment.getProperty("upload.folder"));
	}
}