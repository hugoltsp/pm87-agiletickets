package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;

public class CalculadoraDePrecos {

	public static BigDecimal calcula(Sessao sessao, Integer quantidade) {
		BigDecimal preco = sessao.getPreco();

		switch (sessao.getEspetaculo().getTipo()) {
		case CINEMA:
		case SHOW:
			preco = calculaPrecoUltimoLote(sessao, preco, 0.05, 0.10);
			break;
		case BALLET:
		case ORQUESTRA:
			preco = calculaPrecoUltimoLote(sessao, preco, 0.50, 0.20);

			if (sessao.getDuracaoEmMinutos() > 60) {
				preco = preco.add(sessao.getPreco().multiply(
						BigDecimal.valueOf(0.10)));
			}
			break;
		case TEATRO:
			break;
		}

		return preco.multiply(BigDecimal.valueOf(quantidade));
	}

	private static BigDecimal calculaPrecoUltimoLote(Sessao sessao, BigDecimal preco,
			double taxaOcupacao, double taxaUltimoLote) {

		if (sessao.getOcupacao() <= taxaOcupacao) {
			preco = preco
					.add(preco.multiply(BigDecimal.valueOf(taxaUltimoLote)));
		}
		return preco;
	}

}