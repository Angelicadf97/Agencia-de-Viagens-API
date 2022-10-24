package br.org.com.recode.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class Cliente implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String cpf, nome, tel, senha, email, logradouro, cidade;
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate nasc;
	

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Perfil> perfis = new ArrayList<>();

	public Cliente() {
		super();
	}

	public Cliente(String cpf, String nome, String tel, String senha, String email, String logradouro,
			String cidade, LocalDate nasc) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.tel = tel;
		this.senha = senha;
		this.email = email;
		this.logradouro = logradouro;
		this.cidade = cidade;
		this.nasc = nasc;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public LocalDate getNasc() {
		return nasc;
	}

	public void setNasc(LocalDate nasc) {
		this.nasc = nasc;
	}

	public Cliente id(long id) {
		setId(id);
		return this;
	}

	public Cliente cpf(String cpf) {
		setCpf(cpf);
		return this;
	}

	public Cliente nome(String nome) {
		setNome(nome);
		return this;
	}

	public Cliente tel(String tel) {
		setTel(tel);
		return this;
	}

	public Cliente senha(String senha) {
		setSenha(senha);
		return this;
	}

	public Cliente email(String email) {
		setEmail(email);
		return this;
	}

	public Cliente logradouro(String logradouro) {
		setLogradouro(logradouro);
		return this;
	}

	public Cliente cidade(String cidade) {
		setCidade(cidade);
		return this;
	}

	public Cliente nasc(LocalDate nasc) {
		setNasc(nasc);
		return this;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cidade, cpf, email, id, logradouro, nasc, nome, perfis, senha, tel);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return cidade == other.cidade && Objects.equals(cpf, other.cpf) && Objects.equals(email, other.email)
				&& id == other.id && Objects.equals(logradouro, other.logradouro) && Objects.equals(nasc, other.nasc)
				&& Objects.equals(nome, other.nome) && Objects.equals(perfis, other.perfis)
				&& Objects.equals(senha, other.senha) && Objects.equals(tel, other.tel);
	}


	@Override
	  public Collection<? extends GrantedAuthority> getAuthorities() {
	    return this.perfis;
	  }

	  @Override
	  public String getPassword() {
	    return this.senha;
	  }

	  @Override
	  public String getUsername() {
	    return this.email;
	  }

	  @Override
	  public boolean isAccountNonExpired() {
	    return true;
	  }

	  @Override
	  public boolean isAccountNonLocked() {
	    return true;
	  }

	  @Override
	  public boolean isCredentialsNonExpired() {
	    return true;
	  }

	  @Override
	  public boolean isEnabled() {
	    return true;
	  }

}
