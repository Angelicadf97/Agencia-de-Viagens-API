package br.org.com.recode.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.org.com.recode.model.Cliente;
import br.org.com.recode.model.User;
import br.org.com.recode.repository.ClienteRepository;
import br.org.com.recode.repository.UserRepository;

public class UserAutenticacaoTokenFilter extends OncePerRequestFilter {

	private UserTokenService userTokenService;
	private UserRepository userRepository;

	public UserAutenticacaoTokenFilter(UserTokenService userTokenService, UserRepository userRepository) {

		this.userTokenService = userTokenService;
		this.userRepository = userRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = recuperarToken(request);
		System.out.println("doFilter Internal do usuario" + token);

		boolean valido = userTokenService.isTokenValid(token);
		System.out.println("AutenticacaoTokenFilter do usuario" + valido);
		if (valido) {

			autenticarUser(token);
		}

		filterChain.doFilter(request, response);

	}

	private void autenticarUser(String token) {

		Long idUser = userTokenService.getIdUser(token);
		System.out.println("idUser"+idUser);

		User user = userRepository.findById(idUser).get();

		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null,
				user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);

	}

	private String recuperarToken(HttpServletRequest request) {

		String token = request.getHeader("Authorization");
		System.out.println("recuperarToken do usuario" + token);

		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			System.out.println("AutenticacaoTokenFilter AQUI TBM  do usuario");
			return null;
		} else {
			System.out.println("substring" + token.substring(7, token.length()));
			return token.substring(7, token.length());
		}
	}

}
