package it.unisa.model;

public class Categoria implements Cloneable{
	private String nome;
	
	public Categoria() {
		this.nome = "";
	}
	
	public Categoria(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Categoria clone() {
		try {
			return (Categoria)super.clone();
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
		Categoria other = (Categoria) obj;
		return other.nome.equals(this.nome);
	}
	
	public String toString() {
		return getClass().getSimpleName()+"[nome="+nome+"]";
		
	}
}
