package di.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import service.impl.InCodeProcessor;
import service.impl.ProcessorThread;

@Configuration
@ComponentScan({"controller","model", "service","service.impl"})
public class DIConfiguration {
	
}
