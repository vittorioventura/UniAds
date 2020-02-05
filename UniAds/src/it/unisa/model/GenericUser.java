package it.unisa.model;

public abstract class GenericUser implements Cloneable{
	public static enum Ruolo{AMMINISTRATORE,UTENTE,CORRIERE};
	private String email;
	private String nome;
	private String cognome;
	private String indirizzo;
	private String password;	
	
	public GenericUser() {
		this.email="";
		this.nome="";
		this.cognome="";
		this.indirizzo="";
		this.password="";
	}
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public GenericUser clone() {
		try {
			GenericUser cloned = (GenericUser)super.clone();
			return cloned;
		}
		catch (CloneNotSupportedException e) {
			return null;
		}
	}
	
	public boolean equals(Object obj) {
		if(obj==null)
			return false;
		if(obj.getClass()!=this.getClass())
			return false;
		GenericUser utente = (GenericUser)obj;
		return utente.nome.equals(this.nome) && utente.cognome.equals(this.cognome) && utente.email.equals(this.email) 
				&& utente.indirizzo.equals(this.indirizzo) && utente.password.equals(this.password);
	}
}
