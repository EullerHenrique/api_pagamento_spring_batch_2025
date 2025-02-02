package com.api.pagamento.domain.model.transacao;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.api.pagamento.domain.enumeration.transacao.tipo.TipoTransacao;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

public record Transacao(
		@Id Long id,
		Integer tipo,
		Date data,
		BigDecimal valor,
		Long cpf,
		String cartao,
		Time hora,
		@Column("dono_loja") String donoDaLoja,
		@Column("nome_loja") String nomeDaLoja) {

	public Transacao withValor(BigDecimal valor) {
		var tipoTransacao = TipoTransacao.findByTipo(this.tipo);
		var valorNormalizado = valor
				.divide(new BigDecimal(100))
				.multiply(tipoTransacao.getSinal());
		return new Transacao(
				this.id, this.tipo, this.data, valorNormalizado, this.cpf,
				this.cartao, this.hora, this.donoDaLoja, this.nomeDaLoja);
	}

	public Transacao withData(String data) throws ParseException {
		var dateFormat = new SimpleDateFormat("yyyyMMdd");
		var date = dateFormat.parse(data);

		return new Transacao(
				this.id, this.tipo, new Date(date.getTime()), this.valor, this.cpf,
				this.cartao, this.hora, this.donoDaLoja, this.nomeDaLoja);
	}

	public Transacao withHora(String hora) throws ParseException {
		var dateFormat = new SimpleDateFormat("HHmmss");
		var date = dateFormat.parse(hora);

		return new Transacao(
				this.id, this.tipo, this.data, this.valor, this.cpf,
				this.cartao, new Time(date.getTime()), this.donoDaLoja, this.nomeDaLoja);
	}
}

