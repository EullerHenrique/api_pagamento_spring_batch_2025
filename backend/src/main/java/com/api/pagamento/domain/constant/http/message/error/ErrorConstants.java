package com.api.pagamento.domain.constant.http.message.error;

import com.api.pagamento.domain.constant.divider.DividerConstants;
import com.api.pagamento.domain.constant.http.message.error.word.WordErrorConstants;
import lombok.NoArgsConstructor;

/**
 * Constantes responsáveis por armazenar as constantes de erro
 *
 * @author Euller Henrique
 */
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class ErrorConstants {
	public static final String ERRO_500_SERVIDOR_INTERNO = "Ocorreu um erro interno no servidor ao tentar realizar a operação";
	public static final String ERRO_400_O_CAMPO_XXX_DEVE_SER_DO_TIPO_YYY = WordErrorConstants.O_CAMPO_XXX + DividerConstants.ESPACO + WordErrorConstants.DEVE_SER + DividerConstants.ESPACO + WordErrorConstants.DO_TIPO_YYY;
	public static final String ERRO_400_O_CAMPO_XXX_DEVE_SER_DO_TIPO_YYY_NO_FORMATO = ERRO_400_O_CAMPO_XXX_DEVE_SER_DO_TIPO_YYY + DividerConstants.ESPACO + WordErrorConstants.NO_FORMATO_YYY;
	public static final String ERRO_400_O_CAMPO_XXX_DEVE_SER_UM_DOS_VALORES_YYY = WordErrorConstants.O_CAMPO_XXX + DividerConstants.ESPACO + WordErrorConstants.DEVE_SER + DividerConstants.ESPACO + WordErrorConstants.UM_DOS_VALORES_YYY;
}
