package model.services;

public class TaxaBrasilService implements TaxaService {

	public double taxa(double amount) {
		if (amount <= 100.0) {
			return amount * 0.2;
		} else {
			return amount * 0.15;
		}
	}

}
