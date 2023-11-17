package application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.AluguelCarro;
import model.entities.Veiculo;
import model.services.AluguelService;
import model.services.TaxaBrasilService;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

		System.out.println("Enter com os dados do aluguel");
		System.out.print("Modelo do carro: ");
		String modeloCarro = sc.nextLine();
		System.out.println("Retirada (dd/MM/yyyy hh:mm): ");
		LocalDateTime inicio = LocalDateTime.parse(sc.nextLine(), fmt);
		System.out.println("Retorno (dd/MM/yyyy hh:mm): ");
		LocalDateTime fim = LocalDateTime.parse(sc.nextLine(), fmt);

		AluguelCarro carro = new AluguelCarro(inicio, fim, new Veiculo(modeloCarro));

		System.out.println();
		System.out.print("Entre com preço por hora: ");
		Double precoPorHora = sc.nextDouble();
		System.out.print("Entre com o preço por dia: ");
		Double precoPorDia = sc.nextDouble();

		AluguelService aluguelService = new AluguelService(precoPorHora, precoPorDia,
				new TaxaBrasilService());
		
		aluguelService.processoFatura(carro);
		
		System.out.println("FATURA: ");
		System.out.println("Pagamento básico: " + String.format("%.2f", carro.getFatura().getPagBasico()));
		System.out.println("Imposto: " + String.format("%.2f", carro.getFatura().getTaxa()));
		System.out.println("Pagamento total: " + String.format("%.2f", carro.getFatura().getPagTotal()));
		sc.close();
	}

}
