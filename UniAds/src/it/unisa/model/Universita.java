package it.unisa.model;

public class Universita implements Cloneable {
		private String sigla;
	private String localita;
	
	public Universita() {
		this.sigla = "";
		this.localita = "";
	
	}
	public Universita(String sigla, String localita) {
		this.sigla = sigla;
		this.localita = localita;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getLocalita() {
		return localita;
	}

	public void setLocalita(String localita) {
		this.localita = localita;
	}
	
	public Universita clone() {
		try {
			return (Universita) super.clone();
		}
		catch (CloneNotSupportedException e) {
			return null;
		}
	}
	
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Universita other = (Universita) obj;
		return other.localita.equals(this.localita) && other.sigla.equals(this.sigla);
	}


}
