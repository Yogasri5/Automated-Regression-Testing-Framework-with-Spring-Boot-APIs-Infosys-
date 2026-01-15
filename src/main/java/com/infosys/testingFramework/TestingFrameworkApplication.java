package com.infosys.testingFramework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class TestingFrameworkApplication {

    private static final Logger logger = LoggerFactory.getLogger(TestingFrameworkApplication.class);

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(TestingFrameworkApplication.class, args);
		logger.info("Testing Framework Application started successfully");
		logger.info("Available beans: {}", context.getBeanDefinitionNames().length);
	}

}
