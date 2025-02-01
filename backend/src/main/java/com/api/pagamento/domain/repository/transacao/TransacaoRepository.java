package com.api.pagamento.domain.repository.transacao;

import com.api.pagamento.domain.model.transacao.cnab.TransacaoCnab;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório responsável por conectar a entidade da transação a tabela transacao
 *
 * @author Euller Henrique
 */
public interface TransacaoRepository extends JpaRepository<TransacaoCnab, Long> { }
