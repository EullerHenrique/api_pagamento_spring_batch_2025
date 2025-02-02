package com.api.pagamento.domain.dto.response.sucess_error.error;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Dto responsável por armazenar a estrutura de uma mensagem de erro
 *
 * @author Euller Henrique
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageErrorResponseDto {
	@Schema(example = "500")
	private int status;
	private String error;
	@Schema(example = "Erro interno no servidor")
	private String message;
}


