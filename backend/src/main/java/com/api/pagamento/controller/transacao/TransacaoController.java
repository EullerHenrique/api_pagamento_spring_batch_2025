package com.api.pagamento.controller.transacao;

import com.api.pagamento.domain.annotation.http.sucess_error.ApiResponsesMessageSucessError;
import com.api.pagamento.domain.constant.http.message.error.ErrorConstants;
import com.api.pagamento.domain.constant.http.message.sucess.SucessConstants;
import com.api.pagamento.domain.dto.response.sucess_error.error.MessageErrorResponseDto;
import com.api.pagamento.domain.dto.response.transacao.TransacoesLojaResponseDto;
import com.api.pagamento.domain.exception.http.BadRequestException;
import com.api.pagamento.domain.exception.http.InternalServerErrorException;
import com.api.pagamento.domain.exception.http.NotFoundException;
import com.api.pagamento.service.dto.transacao.TransacaoDtoService;
import com.api.pagamento.domain.dto.response.sucess_error.sucess.MessageSucessResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.api.pagamento.domain.constant.http.code.CodeHttpConstants.INTERNAL_SERVER_ERROR;
import static com.api.pagamento.domain.constant.http.code.CodeHttpConstants.OK;
import static com.api.pagamento.domain.constant.http.message.sucess.SucessConstants.SUCESSO_200_OPERACAO_REALIZADA;
import static com.api.pagamento.domain.constant.http.message.sucess.SucessConstants.SUCESS_200_ARQUIVO_CNAB_SALVO;
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
	@ApiResponsesMessageSucessError
	@PostMapping(value = "/cnab/upload", produces = APPLICATION_JSON, consumes = APPLICATION_MULTIPART_FORM_DATA)
	public ResponseEntity<Object> realizarUploadArquivoCnab(@RequestParam("file") MultipartFile file){

		try {
			transacaoDtoService.realizarUploadArquivoCnab(file);
			MessageSucessResponseDto messageSucessResponseDto = MessageSucessResponseDto.builder().status(200)
					.message(SUCESS_200_ARQUIVO_CNAB_SALVO).build();
			return ResponseEntity.status(200).body(messageSucessResponseDto);
		} catch (NotFoundException | BadRequestException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new InternalServerErrorException(ex);
		}

	}

	/**
	 * Lista as transações
	 *
	 * @return ResponseEntity<Object>
	 *     	ResponseEntity
	 * @author Euller Henrique
	 */
	@Operation(summary = "Lista as transações agrupadas pela loja")
	@ApiResponses(value = { @ApiResponse(responseCode = OK, description = SucessConstants.SUCESSO_200_OPERACAO_REALIZADA, content = {
			@Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = TransacoesLojaResponseDto.class)) }),
			@ApiResponse(responseCode = INTERNAL_SERVER_ERROR, description = ErrorConstants.ERRO_500_SERVIDOR_INTERNO, content = {
					@Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = MessageErrorResponseDto.class)) }) })
	@GetMapping(value = "/listar", produces = APPLICATION_JSON)
	public ResponseEntity<Object> listarTransacoesAgrupadasPelaLoja(){

		try {
			List<TransacoesLojaResponseDto> transacoesAgrupadasPelaLoja = transacaoDtoService.listarTransacoesAgrupadasPelaLoja();
			MessageSucessResponseDto messageSucessResponseDto = MessageSucessResponseDto.builder().status(200)
					.message(SUCESSO_200_OPERACAO_REALIZADA).data(transacoesAgrupadasPelaLoja).build();
			return ResponseEntity.status(200).body(messageSucessResponseDto);
		} catch (NotFoundException | BadRequestException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new InternalServerErrorException(ex);
		}

	}


}
