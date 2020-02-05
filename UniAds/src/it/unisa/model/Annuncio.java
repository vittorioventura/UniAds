package it.unisa.model;

public class Annuncio implements Cloneable{
	private String titolo;
	private String descrizione;
	private String tipo;
	private boolean acquistoOnline;
	private int valutazione;
	private GenericUser utente;
	private Categoria categoria;
	private String siglaUni;
	
	public Annuncio() {
		this.titolo="";
		this.descrizione="";
		this.tipo="";
		this.acquistoOnline=false;
		this.valutazione=0;
		this.utente= null;
		this.categoria= null;
		this.siglaUni="";
	}
	public String getTitolo() {
		return titolo;
	}
	
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public boolean isAcquistoOnline() {
		return acquistoOnline;
	}
	public void setAcquistoOnline(boolean acquistoOnline) {
		this.acquistoOnline = acquistoOnline;
	}
	public int getValutazione() {
		return valutazione;
	}
	public void setValutazione(int valutazione) {
		this.valutazione = valutazione;
	}
	public GenericUser getUtente() {
		return utente.clone();
	}
	public void setUtente(GenericUser utente) {
		this.utente = utente.clone();
	}
	public Categoria getCategoria() {
		return categoria.clone();
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria.clone();
	}
	public String getSiglaUni() {
		return siglaUni;
	}
	public void setSiglaUni(String siglaUni) {
		this.siglaUni = siglaUni;
	}

	public Annuncio clone() {
		try {
			Annuncio cloned = (Annuncio)super.clone();
			cloned.categoria= categoria.clone();
			cloned.utente = utente.clone();
			return cloned;
		}
		catch (CloneNotSupportedException e) {
			return null;
		}
	}
	
}
