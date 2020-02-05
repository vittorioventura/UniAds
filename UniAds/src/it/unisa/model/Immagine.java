package it.unisa.model;

public class Immagine {
	private byte[] img;
	private String nomeImmagine;
	private Annuncio annuncio;
	
	public byte[] getImg() {
		return img;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}
	public Annuncio getAnnuncio() {
		return annuncio;
	}

	public void setAnnuncio(Annuncio annuncio) {
		this.annuncio = annuncio;
	}

	public String getNomeImmagine() {
		return nomeImmagine;
	}

	public void setNomeImmagine(String nomeImmagine) {
		this.nomeImmagine = nomeImmagine;
	}
	
}
