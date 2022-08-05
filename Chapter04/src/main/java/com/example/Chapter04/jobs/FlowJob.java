/*
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
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Michael Minella
 */
@EnableBatchProcessing
@Configuration
public class FlowJob {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Tasklet loadStockFileFlowJob() {
		return (contribution, chunkContext) -> {
			System.out.println("The stock file has been loaded");
			return RepeatStatus.FINISHED;
		};
	}

	@Bean
	public Tasklet loadCustomerFileFlowJob() {
		return (contribution, chunkContext) -> {
			System.out.println("The customer file has been loaded");
			return RepeatStatus.FINISHED;
		};
	}

	@Bean
	public Tasklet updateStartFlowJob() {
		return (contribution, chunkContext) -> {
			System.out.println("The start has been updated");
			return RepeatStatus.FINISHED;
		};
	}

	@Bean
	public Tasklet runBatchTaskletFlowJob() {
		return (contribution, chunkContext) -> {
			System.out.println("The batch has been run");
			return RepeatStatus.FINISHED;
		};
	}

	@Bean
	public Flow preProcessingFlow() {
		return new FlowBuilder<Flow>("preProcessingFlow").start(loadFileStepFlowJob())
				.next(loadCustomerStepFlowJob())
				.next(updateStartStepFlowJob())
				.build();
	}

	@Bean
	public Job conditionalStepLogicJobFlowJob() {
		return this.jobBuilderFactory.get("conditionalStepLogicJob")
				.start(intializeBatchFlowJob())
				.next(runBatchFlowJob())
				.build();
	}

	@Bean
	public Step intializeBatchFlowJob() {
		return this.stepBuilderFactory.get("initalizeBatch")
				.flow(preProcessingFlow())
				.build();
	}

	@Bean
	public Step loadFileStepFlowJob() {
		return this.stepBuilderFactory.get("loadFileStep")
				.tasklet(loadStockFileFlowJob())
				.build();
	}

	@Bean
	public Step loadCustomerStepFlowJob() {
		return this.stepBuilderFactory.get("loadCustomerStep")
				.tasklet(loadCustomerFileFlowJob())
				.build();
	}

	@Bean
	public Step updateStartStepFlowJob() {
		return this.stepBuilderFactory.get("updateStartStep")
				.tasklet(updateStartFlowJob())
				.build();
	}

	@Bean
	public Step runBatchFlowJob() {
		return this.stepBuilderFactory.get("runBatch")
				.tasklet(runBatchTaskletFlowJob())
				.build();
	}

}
