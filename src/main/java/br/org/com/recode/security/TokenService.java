package br.org.com.recode.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.org.com.recode.model.Cliente;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

  @Value("${agencia.jwt.expiration}")
  private String expiration;

  @Value("${agencia.jwt.secret}")
  private String secret;

  public String gerarToken(Authentication authentication) {

    Cliente logado = (Cliente) authentication.getPrincipal();
    System.out.println(logado.getNome());
    System.out.println("passou aqui");
    Date hoje = new Date();
    Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));
    Long id = logado.getId();

    return Jwts.builder()
        .setIssuer("API MM'S")
        .setSubject(id.toString())
        .setIssuedAt(hoje)
        .setExpiration(dataExpiracao)
        .signWith(SignatureAlgorithm.HS256, secret)
        .compact();

  }

  public boolean isTokenValid(String token) {
    
    try {
      Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
      return true;
    } catch (Exception e) {
      System.out.println("Token invalid");
      return false;

    }

  }

  public Long getIdCliente(String token) {

    Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
    return Long.parseLong(claims.getSubject());

  }

}
