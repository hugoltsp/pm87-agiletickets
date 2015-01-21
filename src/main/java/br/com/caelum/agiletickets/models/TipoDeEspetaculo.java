package br.com.caelum.agiletickets.models;

public enum TipoDeEspetaculo {

	CINEMA(0.05, 0.10), SHOW(0.05, 0.10), TEATRO, BALLET(0.50, 0.20, 0.10), ORQUESTRA(
			0.50, 0.20, 0.10);

	private double taxaOcupacao;
	private double taxaUltimoLote;
	private double taxaTempo;

	TipoDeEspetaculo() {
		this(0, 0, 0);
	}

	TipoDeEspetaculo(double taxaOcupacao, double taxaUltimoLote) {
		this(taxaOcupacao, taxaUltimoLote, 0);
	}

	TipoDeEspetaculo(double taxaOcupacao, double taxaUltimoLote,
			double taxaTempo) {
		this.taxaOcupacao = taxaOcupacao;
		this.taxaUltimoLote = taxaUltimoLote;
		this.taxaTempo = taxaTempo;
	}

	public double getTaxaOcupacao() {
		return taxaOcupacao;
	}

	public double getTaxaUltimoLote() {
		return taxaUltimoLote;
	}

	public double getTaxaTempo() {
		return taxaTempo;
	}
}
