package com.api.pagamento.controller.transacao;

import com.api.pagamento.domain.annotation.http.transacao.TransacaoApiResponses;
import com.api.pagamento.domain.exception.http.BadRequestException;
import com.api.pagamento.domain.exception.http.InternalServerErrorException;
import com.api.pagamento.domain.exception.http.NotFoundException;
import com.api.pagamento.service.dto.transacao.TransacaoDtoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.api.pagamento.domain.constant.http.type.TypeHttpConstants.APPLICATION_JSON;

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
	@TransacaoApiResponses
	@PostMapping(value = "/cnab/upload", produces = APPLICATION_JSON, consumes = APPLICATION_JSON)
	public ResponseEntity<Object> realizarUploadCnab(@RequestParam("file") MultipartFile file){

		try {
			return null;
		} catch (NotFoundException | BadRequestException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new InternalServerErrorException(ex);
		}

	}

}
