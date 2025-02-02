package com.api.pagamento.domain.annotation.http.transacao;

/**
 * Anotação responsável por armazenar as respostas http do controller de transação para requisições POST e PUT
 *
 * @author Euller Henrique
@Target({ ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@ApiResponses(value = { @ApiResponse(responseCode = OK, description = SucessConstants.SUCESSO_200_OPERACAO_REALIZADA, content = {
		@Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = TransacaoResponseDto.class)) }),
		@ApiResponse(responseCode = NOT_FOUND, description = ErrorConstants.ERRO_404_TRANSACAO_NAO_ENCONTRADA, content = {
				@Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = MessageErrorResponseDto.class)) }),
		@ApiResponse(responseCode = BAD_REQUEST, description = ErrorConstants.ERRO_400_CAMPOS_PREENCHIDOS_INCORRETAMENTE, content = {
				@Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = MessageErrorResponseDto.class)) }),
		@ApiResponse(responseCode = INTERNAL_SERVER_ERROR, description = ErrorConstants.ERRO_500_SERVIDOR_INTERNO, content = {
				@Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = MessageErrorResponseDto.class)) }) })
public @interface TransacaoApiResponses {}
 */
