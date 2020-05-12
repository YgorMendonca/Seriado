package br.com.faculdadedelta.modelo;

public class Genero {
	
	private long id;
	private String descGenero;
	
	public Genero() {
		super();
	}
	public Genero(long id, String descGenero) {
		super();
		this.id = id;
		this.descGenero = descGenero;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDescGenero() {
		return descGenero;
	}
	public void setDescGenero(String descGenero) {
		this.descGenero = descGenero;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Genero other = (Genero) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
}
