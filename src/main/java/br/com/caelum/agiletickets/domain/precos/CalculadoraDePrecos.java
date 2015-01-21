package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;

public class CalculadoraDePrecos {

	public static BigDecimal calcula(Sessao sessao, Integer quantidade) {
		BigDecimal preco = sessao.getPreco();
		TipoDeEspetaculo tipo = sessao.getEspetaculo().getTipo();

		preco = calculaPrecoUltimoLote(preco, sessao.getPorcentagemOcupacao(),
				tipo);

		if (sessao.getDuracaoEmMinutos() != null
				&& sessao.getDuracaoEmMinutos() > 60) {
			preco = preco.add(sessao.getPreco().multiply(
					BigDecimal.valueOf(tipo.getTaxaTempo())));
		}

		return preco.multiply(BigDecimal.valueOf(quantidade));
	}

	private static BigDecimal calculaPrecoUltimoLote(BigDecimal preco,
			double taxaOcupacaoSessao, TipoDeEspetaculo tipo) {

		if (taxaOcupacaoSessao <= tipo.getTaxaOcupacao()) {
			preco = preco.add(preco.multiply(BigDecimal.valueOf(tipo
					.getTaxaUltimoLote())));
		}

		return preco;
	}

}