package it.unisa.model;

public class Amministratore extends GenericUser{
	private Ruolo ruolo;
	
	public Amministratore() {
		super();
		this.ruolo=Ruolo.AMMINISTRATORE;
	}
	
	public Ruolo getRuolo() {
		return ruolo;
	}
	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}

	
	public boolean equals(Object obj) {
		Amministratore utente = (Amministratore)obj;
		return super.equals(obj) && utente.ruolo.equals(this.ruolo);
	}
}
