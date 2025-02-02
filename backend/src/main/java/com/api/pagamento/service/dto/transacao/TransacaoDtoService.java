package com.api.pagamento.service.dto.transacao;

import com.api.pagamento.domain.dto.response.transacao.TransacoesLojaResponseDto;
import com.api.pagamento.domain.model.transacao.Transacao;
import com.api.pagamento.domain.repository.transacao.TransacaoRepository;
import com.api.pagamento.service.model.transacao.TransacaoModelService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Serviço responsável por retornar dto(s) ou lançar exceção (Se não existir ou se alguma validação falhar)
 *
 * @author Euller Henrique
 */
@Service
public class TransacaoDtoService {

	private final Path fileStorageLocation;
	private final JobLauncher jobLauncher;
	private final Job job;
	private final TransacaoModelService transacaoModelService;

	public TransacaoDtoService(
			@Qualifier("jobLauncherAsync") JobLauncher jobLauncher,
			Job job,
			@Value("${file.upload-dir}") String fileUploadDir,
			TransacaoModelService transacaoModelService
			) {
		this.jobLauncher = jobLauncher;
		this.job = job;
		this.fileStorageLocation = Paths.get(fileUploadDir);
		this.transacaoModelService = transacaoModelService;
	}

	public void realizarUploadArquivoCnab(MultipartFile file)
			throws IOException, JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException,
			JobRestartException {
		String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
		Path targetLocation = fileStorageLocation.resolve(fileName);
		Files.createDirectories(targetLocation.getParent());
		file.transferTo(targetLocation);

		JobParameters jobParameters = new JobParametersBuilder()
				.addJobParameter("cnab", file.getOriginalFilename(), String.class, true)
				.addJobParameter("cnabFile", "file:" + targetLocation, String.class, false)
				.toJobParameters();
		jobLauncher.run(job, jobParameters);
	}

	public List<TransacoesLojaResponseDto> listarTransacoesAgrupadasPelaLoja() {
		List<Transacao> transacoes = transacaoModelService.listarTransacoesOrdenadasPelaLoja();

		Map<String, TransacoesLojaResponseDto> reportMap = new LinkedHashMap<>();

		transacoes.forEach(transacao -> {
			var nomeDaLoja = transacao.nomeDaLoja();
			var valor = transacao.valor();

			reportMap.compute(nomeDaLoja, (key, existingReport) -> {
				TransacoesLojaResponseDto report = (existingReport != null) ? existingReport
						: new TransacoesLojaResponseDto(BigDecimal.ZERO, key, new ArrayList<>());
				return report.addTotal(valor).addTransacao(transacao);
			});
		});

		return new ArrayList<>(reportMap.values());
	}
}
