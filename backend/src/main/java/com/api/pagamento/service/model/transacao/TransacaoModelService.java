package com.api.pagamento.service.model.transacao;

import com.api.pagamento.domain.model.transacao.Transacao;
import com.api.pagamento.domain.repository.transacao.TransacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Serviço responsável por retornar model(s) (se existir) ou  lançar exceção (se não existir)
 *
 * @author Euller Henrique
 */
@Component
@RequiredArgsConstructor
public class TransacaoModelService {
	private final TransacaoRepository transacaoRepository;

	public List<Transacao> listarTransacoesAgrupadasPelaLoja() {
		return transacaoRepository.findAllByOrderByNomeDaLojaAscIdDesc();
	}

}