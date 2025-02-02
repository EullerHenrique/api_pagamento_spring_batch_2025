package com.api.pagamento.config.batch;

import com.api.pagamento.domain.dto.request.transacao.cnab.TransacaoCnabRequestDto;
import com.api.pagamento.domain.model.transacao.Transacao;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.math.BigDecimal;

@Configuration
@RequiredArgsConstructor
public class BatchConfig {

	private final PlatformTransactionManager transactionManager;
	private final JobRepository jobRepository;

	@Bean
	Job job(Step step) {
		return new JobBuilder("job", jobRepository)
				.start(step)
				.incrementer(new RunIdIncrementer())
				.build();
	}

	@Bean
	public Step step(ItemReader<TransacaoCnabRequestDto> reader,
			ItemProcessor<TransacaoCnabRequestDto, Transacao> processor,
			ItemWriter<Transacao> writer) {
		return new StepBuilder("step", jobRepository)
				.<TransacaoCnabRequestDto, Transacao>chunk(1000, transactionManager)
				.reader(reader)
				.processor(processor)
				.writer(writer)
				.build();
	}

	@StepScope
	@Bean
	FlatFileItemReader<TransacaoCnabRequestDto> reader(
			@Value("#{jobParameters['cnabFile']}") Resource resource) {
		return new FlatFileItemReaderBuilder<TransacaoCnabRequestDto>()
				.name("reader")
				.resource(resource)
				.fixedLength()
				.columns(new Range(1, 1), new Range(2, 9), new Range(10, 19), new Range(20, 30),
						new Range(31, 42), new Range(43, 48), new Range(49, 62), new Range(63, 80))
				.names("tipo", "data", "valor", "cpf", "cartao", "hora", "donoDaLoja", "nomeDaLoja")
				.targetType(TransacaoCnabRequestDto.class)
				.build();
	}


	@Bean
	ItemProcessor<TransacaoCnabRequestDto, Transacao> processor() {
		return item -> new Transacao(
				null, item.tipo(), null,
				item.valor().divide(BigDecimal.valueOf(100)),
				item.cpf(), item.cartao(), null,
				item.donoDaLoja().trim(), item.nomeDaLoja().trim())
				.withData(item.data())
				.withHora(item.hora());
	}


	@Bean
	JdbcBatchItemWriter<Transacao> writer(DataSource dataSource) {
		return new JdbcBatchItemWriterBuilder<Transacao>()
				.dataSource(dataSource)
				.sql("""
				  INSERT INTO transacao (
					tipo, data, valor, cpf, cartao,
					hora, dono_loja, nome_loja
				  ) VALUES (
					:tipo, :data, :valor, :cpf, :cartao,
					:hora, :donoDaLoja, :nomeDaLoja
				  )
				""")
				.beanMapped()
				.build();
	}

	/*
	@Bean
	JobLauncher jobLauncherAsync(JobRepository jobRepository) throws Exception {
		TaskExecutorJobLauncher jobLauncher = new TaskExecutorJobLauncher();
		jobLauncher.setJobRepository(jobRepository);
		jobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor());
		jobLauncher.afterPropertiesSet();
		return jobLauncher;
	}
	 */

}
