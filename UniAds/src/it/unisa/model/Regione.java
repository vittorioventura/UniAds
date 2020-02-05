package it.unisa.model;

public class Regione {
	private String nome;
	
	public Regione() {
		this.nome = "";
	}
	
	public Regione(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}


