package com.shaffer.integrationhelper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.shaffer.integrationhelper.controller.MainController;
import com.shaffer.integrationhelper.service.IProcessor;
import com.shaffer.integrationhelper.service.IValidator;
import com.shaffer.integrationhelper.service.impl.InCodeValidator;
import com.shaffer.integrationhelper.service.impl.ProcessorThread;

@Configuration
@ComponentScan(basePackages = { "com.shaffer.integrationhelper", "com.shaffer.integrationhelper.controller",
		"com.shaffer.integrationhelper.model", "com.shaffer.integrationhelper.service",
		"com.shaffer.integrationhelper.service.impl", "com.shaffer.integrationhelper.view" })
public class DIConfiguration {
	@Bean
	public MainController mainController() {
		return new MainController(null, null);
	}

	@Bean
	public IProcessor processorThreadService() {
		return new ProcessorThread();
	}

	@Bean
	public IValidator validator() {
		return new InCodeValidator();
	}
}
