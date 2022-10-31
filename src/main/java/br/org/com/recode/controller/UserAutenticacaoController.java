package br.org.com.recode.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.com.recode.controller.dto.UserTokenDTO;
import br.org.com.recode.controller.form.LoginForm;
import br.org.com.recode.security.UserTokenService;

@RestController
@RequestMapping("/user/auth")
public class UserAutenticacaoController {
	
	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private UserTokenService userTokenService;

	@PostMapping
	public ResponseEntity<?> autenticar(@RequestBody @Valid LoginForm form) {

		UsernamePasswordAuthenticationToken dadosLogin = new UsernamePasswordAuthenticationToken(form.getEmail(),
				form.getSenha());
		System.out.println(form.getSenha());
		System.out.println(form.getEmail());
		try {
			Authentication authentication = authManager.authenticate(dadosLogin);
			String token = userTokenService.gerarToken(authentication);
			System.out.println(token);
			System.out.println("passou aqui");
			return ResponseEntity.ok(new UserTokenDTO(token, "Bearer", authentication));

		} catch (Exception e) {

			System.out.println("user     AutenticacaoController dando ruim aqui");
			return ResponseEntity.badRequest().build();
		}

	}
}
