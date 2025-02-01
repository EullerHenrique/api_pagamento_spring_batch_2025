package com.api.pagamento.service.dto.transacao;

import com.api.pagamento.domain.converter.Converter;
import com.api.pagamento.service.model.transacao.TransacaoModelService;
import com.api.pagamento.service.util.transacao.TransacaoUtilService;
import com.api.pagamento.service.valid.transacao.TransacaoValidatorService;
import com.api.pagamento.domain.dto.request.transacao.cnab.TransacaoCnabRequestDto;
import com.api.pagamento.domain.dto.response.transacao.TransacaoResponseDto;
import com.api.pagamento.domain.model.transacao.cnab.TransacaoCnab;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

	/**
	 * Busca uma transação
	 *
	 * @param id
	 * 		Id da transação
	 * @return TransacaoResponseDto
     *      Dto com os dados de resposta da transação
	 * @author Euller Henrique
	 */
	public TransacaoResponseDto buscarTransacao(Long id) {
		TransacaoCnab transacao = transacaoModelService.buscarTransacao(id);
		return converter.originToDestiny(transacao, TransacaoResponseDto.class);
	}

	/**
	 * Lista as transacoes
	 *
	 * @return List<TransacaoResponseDto>
     *     Lista de dtos com os dados de resposta das transações
	 * @author Euller Henrique
	 */
	public List<TransacaoResponseDto> listarTransacoes() {
		List<TransacaoCnab> transacoes = transacaoModelService.listarTransacoes();
		return converter.originToDestiny(transacoes, TransacaoResponseDto.class);
	}

	/**
	 * Realiza um pagamento
	 *
	 * @param request
	 * 		Dto com os dados de requisição da transação
	 * @return TransacaoResponseDto
     *      Dto com os dados da resposta da transação
	 * @author Euller Henrique
     */
	public TransacaoResponseDto pagar(TransacaoCnabRequestDto request) {
		transacaoValidatorService.validarTipoPagamentoAoPagar(request);

		TransacaoResponseDto transacaoResponseDto = converter.originToDestiny(request, TransacaoResponseDto.class);

		transacaoResponseDto.getDescricao().setNsu(transacaoUtilService.obterNsu());
		transacaoResponseDto.getDescricao().setCodigoAutorizacao(transacaoUtilService.obterCodigoAutorizacao());
		transacaoResponseDto.getDescricao().setStatus(transacaoUtilService.obterStatusAoPagar());
		TransacaoCnab transacaoNaoSalva = converter.originToDestiny(transacaoResponseDto, TransacaoCnab.class);
		transacaoResponseDto.setId(transacaoModelService.salvarTransacao(transacaoNaoSalva).toString());

		return transacaoResponseDto;
	}

	/**
	 * Estorna a transação
     *
     * @param id
     *      Id da transação
     * @return TransacaoResponseDto
     *      Dto com os dados de resposta da transação
	 * @author Euller Henrique
     */
	public TransacaoResponseDto estornar(Long id) {
		TransacaoCnab transacao = transacaoModelService.buscarTransacao(id);
		transacaoValidatorService.validarStatusTransacaoAoEstornar(transacao);

		transacao.getDescricao().setStatus(transacaoUtilService.obterStatusAoEstornar());
		transacaoModelService.salvarTransacao(transacao);

		return converter.originToDestiny(transacao, TransacaoResponseDto.class);
	}

}
