package it.unisa.model;

public class Media {
	private Immagine immagine;
	private Video video;
	private GenericUser utente;
	private Annuncio annuncio;
	
	
	public GenericUser getUtente() {
		return utente;
	}
	public void setUtente(GenericUser utente) {
		this.utente = utente;
	}
	public Annuncio getAnnuncio() {
		return annuncio;
	}
	public void setAnnuncio(Annuncio annuncio) {
		this.annuncio = annuncio;
	}
	public Immagine getImmagine() {
		return immagine;
	}
	public void setImmagine(Immagine immagine) {
		this.immagine = immagine;
	}
	public Video getVideo() {
		return video;
	}
	public void setVideo(Video video) {
		this.video = video;
	}
	
	
}
