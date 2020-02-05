package it.unisa.model;

public class Preferiti {
	private String titoloAnnuncio="";
	private String emailUtenteAnnuncio="";
	private String emailUtente="";
	 public Preferiti() {
		 
	 }
	
	
	
	public String getEmailUtente() {
		return emailUtente;
	}
	
	public void setEmailUtente(String emailUtente) {
		this.emailUtente = emailUtente;
	}


	public String getEmailUtenteAnnuncio() {
		return emailUtenteAnnuncio;
	}


	public void setEmailUtenteAnnuncio(String emailUtenteAnnuncio) {
		this.emailUtenteAnnuncio = emailUtenteAnnuncio;
	}



	public String getTitoloAnnuncio() {
		return titoloAnnuncio;
	}



	public void setTitoloAnnuncio(String titoloAnnuncio) {
		this.titoloAnnuncio = titoloAnnuncio;
	}
	
	
	
}
