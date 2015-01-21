package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;

public class CalculadoraDePrecos {

	public static BigDecimal calcula(Sessao sessao, Integer quantidade) {
		BigDecimal preco;

		switch (sessao.getEspetaculo().getTipo()) {
		case CINEMA:
		case SHOW:
			// quando estiver acabando os ingressos...
			preco = calculaPreco(sessao, 0.05, 0.10);
			break;
		case BALLET:
		case ORQUESTRA:
			preco = calculaPreco(sessao, 0.50, 0.20);

			if (sessao.getDuracaoEmMinutos() > 60) {
				preco = preco.add(sessao.getPreco().multiply(
						BigDecimal.valueOf(0.10)));
			}
			break;

		default:
			// nao aplica aumento para teatro (quem vai é pobretão)
			preco = sessao.getPreco();
		}

		return preco.multiply(BigDecimal.valueOf(quantidade));
	}

	private static BigDecimal calculaPreco(Sessao sessao, double taxa1, double taxa2) {
		BigDecimal preco;
		if ((sessao.getTotalIngressos() - sessao.getIngressosReservados())
				/ sessao.getTotalIngressos().doubleValue() <= taxa1) {
			preco = sessao.getPreco().add(
					sessao.getPreco().multiply(BigDecimal.valueOf(taxa2)));
		} else {
			preco = sessao.getPreco();
		}
		return preco;
	}

}