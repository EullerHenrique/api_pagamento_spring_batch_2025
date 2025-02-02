package com.api.pagamento.service.dto.transacao;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * Serviço responsável por retornar dto(s) ou lançar exceção (Se não existir ou se alguma validação falhar)
 *
 * @author Euller Henrique
 */
@Service
@RequiredArgsConstructor
public class TransacaoDtoService {

	@Value("${file.upload-dir}")
	private String fileUploadDir;

	public void realizarUploadArquivoCnab(MultipartFile file) throws IOException {
		String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
		Path targerLocation = Paths.get(fileUploadDir).resolve(fileName);
		file.transferTo(targerLocation);
	}

	public void realizarPagamentosPeloCnab(MultipartFile file) {

	}

}
