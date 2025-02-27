/**
 * Copyright 2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.Chapter04.jobs;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.Chapter04.batch.HelloWorldTasklet;

/**
 * @author Michael Minella
 */
@EnableBatchProcessing
@Configuration
public class ExecutionContextJobClass {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job helloWorldBatchJob() {
		return this.jobBuilderFactory.get("helloWorldBatchJob")
				.start(helloWorldStep())
				.build();
	}

	@Bean
	public Step helloWorldStep() {
		return this.stepBuilderFactory.get("helloWorldStep")
				.tasklet(taskletExecutionContext())
				.build();
	}

	@StepScope
	@Bean
	public HelloWorldTasklet taskletExecutionContext() {
		return new HelloWorldTasklet();
	}

}
