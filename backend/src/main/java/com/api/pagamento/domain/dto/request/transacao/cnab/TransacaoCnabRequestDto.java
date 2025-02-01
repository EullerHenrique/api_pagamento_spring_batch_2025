package com.api.pagamento.domain.dto.request.transacao.cnab;

import java.math.BigDecimal;

/**
 *
 * DTO responsável por representar os dados de uma transação CNAB
 *
 * @author Euller Henrique
 */
public record TransacaoCnabRequestDto(
	Integer tipo,
	String data,
	BigDecimal valor,
	Long cpf,
	String cartao,
	String hora,
	String donoDaLoja,
	String nomeDaLoja){
}
