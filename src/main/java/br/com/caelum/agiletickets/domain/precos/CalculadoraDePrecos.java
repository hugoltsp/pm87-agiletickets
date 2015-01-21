package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;

public class CalculadoraDePrecos {

	public static BigDecimal calcula(Sessao sessao, Integer quantidade) {
		return calculaPrecoUltimoLote(sessao, quantidade);
	}

	private static BigDecimal calculaPrecoUltimoLote(Sessao sessao,
			Integer quantidade) {

		BigDecimal preco = sessao.getPreco();
		TipoDeEspetaculo tipo = sessao.getEspetaculo().getTipo();

		if (sessao.getPorcentagemOcupacao() <= tipo.getTaxaOcupacao()) {
			preco = preco.add(preco.multiply(BigDecimal.valueOf(tipo
					.getTaxaUltimoLote())));
		}

		if (sessao.getDuracaoEmMinutos() != null
				&& sessao.getDuracaoEmMinutos() > 60) {
			preco = preco.add(sessao.getPreco().multiply(
					BigDecimal.valueOf(tipo.getTaxaTempo())));
		}

		return preco.multiply(BigDecimal.valueOf(quantidade));
	}

}