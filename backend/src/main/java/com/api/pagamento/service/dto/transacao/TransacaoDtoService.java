package com.api.pagamento.service.dto.transacao;

import com.api.pagamento.domain.converter.Converter;
import com.api.pagamento.service.model.transacao.TransacaoModelService;
import com.api.pagamento.service.util.transacao.TransacaoUtilService;
import com.api.pagamento.service.valid.transacao.TransacaoValidatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Serviço responsável por retornar dto(s) ou lançar exceção (Se não existir ou se alguma validação falhar)
 *
 * @author Euller Henrique
 */
@Service
@RequiredArgsConstructor
public class TransacaoDtoService {

	private final TransacaoModelService transacaoModelService;
	private final TransacaoUtilService transacaoUtilService;
	private final TransacaoValidatorService transacaoValidatorService;
	private final Converter converter;

}
