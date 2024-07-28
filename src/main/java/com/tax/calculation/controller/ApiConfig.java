package com.tax.calculation.controller;

import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.boot.task.TaskExecutorCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
public class ApiConfig {

	public TaskExecutorCustomizer taskExecutor() {
		ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 1, null, null);
		executor.setCorePoolSize(2);
		executor.setMaximumPoolSize(2);
		return (TaskExecutorCustomizer) executor;
	}
}
 