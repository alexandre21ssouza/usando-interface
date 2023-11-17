package model.services;

import java.time.Duration;

import model.entities.AluguelCarro;
import model.entities.Fatura;

public class AluguelService {

	private Double precoPoHora;
	private Double precoPorDia;
	
	private TaxaService taxaService;
	
	public AluguelService() {
		
	}

	public AluguelService(Double precoPoHora, Double precoPorDia, TaxaService taxaService) {
	
		this.precoPoHora = precoPoHora;
		this.precoPorDia = precoPorDia;
		this.taxaService = taxaService;
	}

	public Double getPrecoPoHora() {
		return precoPoHora;
	}

	public void setPrecoPoHora(Double precoPoHora) {
		this.precoPoHora = precoPoHora;
	}

	public Double getPrecoPorDia() {
		return precoPorDia;
	}

	public void setPrecoPorDia(Double precoPorDia) {
		this.precoPorDia = precoPorDia;
	}

	public TaxaService getTaxaService() {
		return taxaService;
	}

	public void setTaxaService(TaxaService taxaService) {
		this.taxaService = taxaService;
	}
	
	public void processoFatura(AluguelCarro aluguelCarro) {
		
		//Calcula a diferença da data inicial com a final em minutos
		double minutes = Duration.between(aluguelCarro.getInicio(),
				aluguelCarro.getFim()).toMinutes();
		//Transformando em horas
		double hours = minutes / 60.0;
		
		double pagamentoBasico;
		
		if(hours <= 12.0) {
			//Arredondando pra cima as horas
			pagamentoBasico = precoPoHora * Math.ceil(hours);
			
			
		} else {
			pagamentoBasico = precoPorDia * Math.ceil(hours / 24.0);
			
		}
		
		double taxa = taxaService.taxa(pagamentoBasico);
		
		//Instanciando Fatura
		aluguelCarro.setFatura(new Fatura(pagamentoBasico, taxa));
		
	}
	
}
