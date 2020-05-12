package br.com.faculdadedelta.modelo;

public class Seriado {

	private long id;
	private int idStatus;
	private int idGenero;
	private String nome;
	private String comentario;
	private double notaAvaliacao; 
	private Status status;
	private Genero genero;
	
	public Seriado(long id, int idStatus, int idGenero, String nome, String comentario, double notaAvaliacao,
			Status status, Genero genero) {
		super();
		this.id = id;
		this.idStatus = idStatus;
		this.idGenero = idGenero;
		this.nome = nome;
		this.comentario = comentario;
		this.notaAvaliacao = notaAvaliacao;
		this.status = status;
		this.genero = genero;
	}

	public Seriado() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(int idStatus) {
		this.idStatus = idStatus;
	}

	public int getIdGenero() {
		return idGenero;
	}

	public void setIdGenero(int idGenero) {
		this.idGenero = idGenero;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public double getNotaAvaliacao() {
		return notaAvaliacao;
	}

	public void setNotaAvaliacao(double notaAvaliacao) {
		this.notaAvaliacao = notaAvaliacao;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
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
		Seriado other = (Seriado) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
}