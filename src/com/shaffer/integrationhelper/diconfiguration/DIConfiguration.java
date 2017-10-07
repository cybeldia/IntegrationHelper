package com.shaffer.integrationhelper.diconfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.shaffer.integrationhelper.controller.MainController;
import com.shaffer.integrationhelper.service.IProcessorThread;
import com.shaffer.integrationhelper.service.IValidator;
import com.shaffer.integrationhelper.service.impl.InCodeProcessor;
import com.shaffer.integrationhelper.service.impl.ProcessorThread;
import com.shaffer.integrationhelper.service.impl.Validator;

@Configuration
@ComponentScan(basePackages = {
		"com.shaffer.integrationhelper",
		"com.shaffer.integrationhelper.controller",
		"com.shaffer.integrationhelper.model",
		"com.shaffer.integrationhelper.service",
		"com.shaffer.integrationhelper.service.impl",
		"com.shaffer.integrationhelper.view"	
})
public class DIConfiguration {
	@Bean
	public MainController mainController() {
		return new MainController(null, null);
	}
	@Bean
	public IProcessorThread processorThreadService() {
		return new ProcessorThread();
	}
	@Bean public IValidator validator() {
		return new Validator();
	}
}
