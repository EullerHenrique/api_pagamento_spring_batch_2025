package com.api.pagamento.controller.transacao;

import com.api.pagamento.domain.exception.http.BadRequestException;
import com.api.pagamento.domain.exception.http.InternalServerErrorException;
import com.api.pagamento.domain.exception.http.NotFoundException;
import com.api.pagamento.service.dto.transacao.TransacaoDtoService;
import com.api.pagamento.domain.dto.response.sucess_error.sucess.MessageSucessResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import static com.api.pagamento.domain.constant.http.type.TypeHttpConstants.APPLICATION_JSON;
import static com.api.pagamento.domain.constant.http.type.TypeHttpConstants.APPLICATION_MULTIPART_FORM_DATA;

/**
 * Controlador responsável por expor os endpoints relacionados a transação
 *
 * @author Euller Henrique
 */
@RestController
@RequestMapping("/transacao")
@RequiredArgsConstructor
public class TransacaoController {
	private final TransacaoDtoService transacaoDtoService;

	/**
	 * Realiza o upload de um arquivo CNAB
	 *
	 * @param file
	 * 		Arquivo CNAB a ser enviado
	 * @return ResponseEntity<Object>
	 *     	ResponseEntity
	 * @author Euller Henrique
	 */
	@Operation(summary = "Realiza o upload de um arquivo CNAB")
	@PostMapping(value = "/cnab/upload", produces = APPLICATION_JSON, consumes = APPLICATION_MULTIPART_FORM_DATA)
	public ResponseEntity<Object> realizarUploadArquivoCnab(@RequestParam("file") MultipartFile file){

		try {
			transacaoDtoService.realizarUploadArquivoCnab(file);
			MessageSucessResponseDto messageSucessResponseDto = MessageSucessResponseDto.builder().status(200)
					.message("Arquivo CNAB enviado com sucesso").build();
			return ResponseEntity.status(200).body(messageSucessResponseDto);
		} catch (NotFoundException | BadRequestException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new InternalServerErrorException(ex);
		}

	}

	/**
	 * Realiza pagamentos por meio de um arquivo CNAB
	 *
	 * @param file
	 * 		Arquivo CNAB a ser enviado
	 * @return ResponseEntity<Object>
	 *     	ResponseEntity
	 * @author Euller Henrique
	 */
	@Operation(summary = "Realiza pagamentos por meio de um arquivo CNAB")
	@PostMapping(value = "/cnab/pagar", produces = APPLICATION_JSON, consumes = APPLICATION_JSON)
	public ResponseEntity<Object> realizarPagamentosPeloCnab(@RequestParam("file") MultipartFile file){

		try {
			return null;
		} catch (NotFoundException | BadRequestException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new InternalServerErrorException(ex);
		}

	}

}
