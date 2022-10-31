package br.org.com.recode.controller.dto;

import org.springframework.security.core.Authentication;

import br.org.com.recode.model.User;

public class UserTokenDTO {
	
	private Long idUser;
	private String emailUser;

	private String token;
	private String tipo;

	public UserTokenDTO(String token, String tipo, Authentication authentication) {
		User logado = (User) authentication.getPrincipal();
		this.token = token;
		this.tipo = tipo;

		this.idUser = logado.getId();
		this.emailUser = logado.getEmail();

	}

	public String getToken() {
		return this.token;
	}

	public String getTipo() {
		return this.tipo;
	}

	public Long getIdUser() {
		return this.idUser;
	}

	public String getEmailUser() {
		return this.emailUser;
	}
}
