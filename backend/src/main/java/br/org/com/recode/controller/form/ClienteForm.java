package br.org.com.recode.controller.form;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.org.com.recode.model.Cliente;

public class ClienteForm {

	@NotNull
	@NotEmpty
	@NotBlank
	private String cpf;
	@NotNull
	@NotEmpty
	@NotBlank
	private String nome;
	@NotNull
	@NotEmpty
	private String tel;
	@NotNull
	@NotEmpty
	private String email;
	@NotNull
	@NotEmpty
	private String senha;
	@NotNull
	@NotEmpty
	private String logradouro;
	@NotNull
	@NotEmpty
	private String cidade;
	private LocalDate nasc;
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
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
	
	public Cliente converter(BCryptPasswordEncoder encoder) {

	    this.setSenha(encoder.encode(senha));
	    return new Cliente(cpf, nome, tel, senha, email, logradouro, cidade, nasc);
	  }
}
