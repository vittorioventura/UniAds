package it.unisa.model;

public class Video {
	private byte [] video;
	private Annuncio annuncio;
	private String nomeVideo;

	public byte [] getVideo() {
		return video;
	}

	public void setVideo(byte [] video) {
		this.video = video;
	}
	public Annuncio getAnnuncio() {
		return annuncio;
	}

	public void setAnnuncio(Annuncio annuncio) {
		this.annuncio = annuncio;
	}

	public String getNomeVideo() {
		return nomeVideo;
	}

	public void setNomeVideo(String nomeVideo) {
		this.nomeVideo = nomeVideo;
	}
}
