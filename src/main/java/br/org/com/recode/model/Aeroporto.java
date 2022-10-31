package br.org.com.recode.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Aeroporto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String cnpj, nome, cidade;
	
	public Aeroporto() {
	}
	public Aeroporto(long id, String cnpj, String nome, String cidade) {
		this.id = id;
		this.cnpj = cnpj;
		this.nome = nome;
		this.cidade = cidade;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(cidade, cnpj, id, nome);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aeroporto other = (Aeroporto) obj;
		return Objects.equals(cidade, other.cidade) && Objects.equals(cnpj, other.cnpj) && id == other.id
				&& Objects.equals(nome, other.nome);
	}
}
