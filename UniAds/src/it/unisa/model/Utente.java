package it.unisa.model;

public class Utente extends GenericUser {
	private Ruolo  ruolo;
	
	public Utente() {
		super();
		this.ruolo=Ruolo.UTENTE;
	}
	
	public Ruolo getRuolo() {
		return ruolo;
	}
	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}
	public boolean equals(Object obj) {
		Utente utente = (Utente)obj;
		return super.equals(obj) && utente.ruolo.equals(this.ruolo);
	}

}
