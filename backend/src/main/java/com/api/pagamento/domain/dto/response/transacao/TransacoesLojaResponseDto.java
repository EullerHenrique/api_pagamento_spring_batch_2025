package com.api.pagamento.domain.dto.response.transacao;

import com.api.pagamento.domain.model.transacao.Transacao;

import java.math.BigDecimal;
import java.util.List;

public record TransacoesLojaResponseDto(
		BigDecimal total,
		String nomeDaLoja,
		List<Transacao> transacoes) {

	public TransacoesLojaResponseDto addTotal(BigDecimal valor) {
		return new TransacoesLojaResponseDto(this.total().add(valor), this.nomeDaLoja, this.transacoes);
	}

	public TransacoesLojaResponseDto addTransacao(Transacao transacao) {
		var transacoes = this.transacoes();
		transacoes.add(transacao);
		return new TransacoesLojaResponseDto(this.total(), this.nomeDaLoja, transacoes);
	}

	public TransacoesLojaResponseDto withNomeDaLoja(String nomeDaLoja) {
		return new TransacoesLojaResponseDto(this.total(), nomeDaLoja, this.transacoes);
	}
}