package it.unisa.model;

public class AreaCompetenza {
	private String emailCorriere;
	private String nomeRegione;
	private String agenzia;
	private double prezzo;
	
	public AreaCompetenza() {
		
	}


	public String getAgenzia() {
		return agenzia;
	}


	public void setAgenzia(String agenzia) {
		this.agenzia = agenzia;
	}


	public double getPrezzo() {
		return prezzo;
	}


	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}


	public String getEmailCorriere() {
		return emailCorriere;
	}


	public void setEmailCorriere(String emailCorriere) {
		this.emailCorriere = emailCorriere;
	}


	public String getNomeRegione() {
		return nomeRegione;
	}


	public void setNomeRegione(String nomeRegione) {
		this.nomeRegione = nomeRegione;
	}
	
	
}
