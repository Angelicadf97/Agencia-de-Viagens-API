package br.org.com.recode.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.org.com.recode.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class UserTokenService {

	@Value("${agencia.jwt.expiration}")
	private String expiration;

	@Value("${agencia.jwt.secret}")
	private String secret;

	public String gerarToken(Authentication authentication) {

		User logado = (User) authentication.getPrincipal();
		System.out.println(logado.getNome());
		System.out.println("gerou o token do usuario");
		Date hoje = new Date();
		Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));
		Long id = logado.getId();

		return Jwts.builder().setIssuer("API MM'S").setSubject(id.toString()).setIssuedAt(hoje)
				.setExpiration(dataExpiracao).signWith(SignatureAlgorithm.HS256, secret).compact();

	}

	public boolean isTokenValid(String token) {
		System.out.println("Token do usuario valido"+ token);
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			System.out.println("Token do usuario invalido");
			return false;

		}

	}

	public Long getIdUser(String token) {

		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());

	}

}
