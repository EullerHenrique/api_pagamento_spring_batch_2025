package com.api.pagamento.domain.dto.response.sucess_error.sucess;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Dto responsável por armazenar a estrutura de uma mensagem de sucesso
 *
 * @author Euller Henrique
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageSucessResponseDto {
	@Schema(example = "200")
	private int status;
	@Schema()
	private Object data;
	@Schema(example = "Operação realizada com sucesso!")
	private String message;
}
