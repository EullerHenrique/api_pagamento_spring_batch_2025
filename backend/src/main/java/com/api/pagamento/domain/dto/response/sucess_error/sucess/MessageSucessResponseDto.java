package com.api.pagamento.domain.dto.response.sucess_error.sucess;

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
	private int status;
	private String message;
	private Object data;
}
