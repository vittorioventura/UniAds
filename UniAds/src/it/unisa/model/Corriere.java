package it.unisa.model;

public class Corriere extends GenericUser{
	private String nomeAgenzia;
	private Ruolo ruolo;
	public Corriere() {
		super();
		this.setRuolo(Ruolo.CORRIERE);
	}
	public Ruolo getRuolo() {
		return ruolo;
	}
	
	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}
	public String getNomeAgenzia() {
		return nomeAgenzia;
	}
	public void setNomeAgenzia(String nomeAgenzia) {
		this.nomeAgenzia = nomeAgenzia;
	}

}
