package model.entities;

public class Veiculo {

	private String modeloCarro;
	
	public Veiculo() {
		
	}

	public Veiculo(String modeloCarro) {
		
		this.modeloCarro = modeloCarro;
	}
	

	public String getModelo() {
		return modeloCarro;
	}

	public void setModelo(String modeloCarro) {
		this.modeloCarro = modeloCarro;
	}
	
	
	
}
