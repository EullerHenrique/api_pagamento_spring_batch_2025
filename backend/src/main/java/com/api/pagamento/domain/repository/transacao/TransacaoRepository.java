package com.api.pagamento.domain.repository.transacao;

import com.api.pagamento.domain.model.transacao.Transacao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransacaoRepository extends CrudRepository<Transacao, Long> {

	List<Transacao> findAllByOrderByNomeDaLojaAscIdDesc();
}