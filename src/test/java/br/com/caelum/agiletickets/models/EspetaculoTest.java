package br.com.caelum.agiletickets.models;

import static org.junit.Assert.*;

import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Test;

public class EspetaculoTest {

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(5));
	}

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(6));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(15));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(5, 3));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(10, 3));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(5, 3));
	}
	
	@Test
	public void deveCriarUmaSessaoSeInicioIgualAFim() throws Exception {
		Espetaculo espetaculoUnico = new Espetaculo();
		
		LocalDate inicio = new LocalDate(2015,1,22);
		LocalDate fim = new LocalDate(2015,1,22);
		LocalTime horario = new LocalTime(22, 00);
		Periodicidade periodicidade = Periodicidade.DIARIA;
		
		List<Sessao> sessoes = espetaculoUnico.criaSessoes(inicio, fim, horario, periodicidade);
		assertEquals(1, sessoes.size());	
		
		Sessao sessao = sessoes.get(0);
		
		assertEquals(new DateTime(2015, 1, 22, 22, 00), sessao.getInicio());
		assertEquals(espetaculoUnico,sessao.getEspetaculo());
	}
	
	@Test
	public void deveCriarDuasSessoes() throws Exception {
		Espetaculo espetaculoUnico = new Espetaculo();
		
		LocalDate inicio = new LocalDate(2015,1,22);
		LocalDate fim = new LocalDate(2015,1,23);
		LocalTime horario = new LocalTime(22, 00);
		Periodicidade periodicidade = Periodicidade.DIARIA;
		
		List<Sessao> sessoes = espetaculoUnico.criaSessoes(inicio, fim, horario, periodicidade);

		assertEquals(2, sessoes.size());
		
		Sessao sessao = sessoes.get(0);
		
		Sessao segundaSessao = sessoes.get(1);
		
		assertEquals(new DateTime(2015, 1, 22, 22, 00), sessao.getInicio());
		assertEquals(new DateTime(2015, 1,23,22,00), segundaSessao.getInicio());
		
		assertEquals(espetaculoUnico, sessao.getEspetaculo());
		assertEquals(espetaculoUnico, segundaSessao.getEspetaculo());
	}
	
	@Test
	public void deveCriarTresSessoes() throws Exception {
		Espetaculo espetaculoUnico = new Espetaculo();
		
		LocalDate inicio = new LocalDate(2015,1,22);
		LocalDate fim = new LocalDate(2015,1,24);
		LocalTime horario = new LocalTime(22, 00);
		Periodicidade periodicidade = Periodicidade.DIARIA;
		
		List<Sessao> sessoes = espetaculoUnico.criaSessoes(inicio, fim, horario, periodicidade);

		assertEquals(3, sessoes.size());
		
		Sessao sessao = sessoes.get(0);
		Sessao segundaSessao = sessoes.get(1);
		Sessao terceiraSessao = sessoes.get(2);
		
		assertEquals(new DateTime(2015, 1, 22, 22, 00), sessao.getInicio());
		assertEquals(new DateTime(2015, 1,23,22,00), segundaSessao.getInicio());
		assertEquals(new DateTime(2015, 1,24,22,00), terceiraSessao.getInicio());

		assertEquals(espetaculoUnico, sessao.getEspetaculo());
		assertEquals(espetaculoUnico, segundaSessao.getEspetaculo());
		assertEquals(espetaculoUnico, terceiraSessao.getEspetaculo());

	}
	
	@Test
	public void deveCriarUmaSessaoSemanal() throws Exception {
		Espetaculo espetaculo = new Espetaculo();

		LocalDate inicio = new LocalDate(2015,1,22);
		LocalDate fim = new LocalDate(2015,1,24);
		LocalTime horario = new LocalTime(22, 00);
		Periodicidade periodicidade = Periodicidade.SEMANAL;
		
		List<Sessao> sessoes = espetaculo.criaSessoes(inicio, fim, horario, periodicidade);
	
		assertEquals(1, sessoes.size());
		
		assertEquals(new DateTime(2015,1,22,22,00),sessoes.get(0).getInicio());
	}
	
	private Sessao sessaoComIngressosSobrando(int quantidade) {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(quantidade * 2);
		sessao.setIngressosReservados(quantidade);

		return sessao;
	}
	
}
