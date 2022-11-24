package br.org.com.recode.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.org.com.recode.model.User;
import br.org.com.recode.repository.UserRepository;

@Service
public class UserAutenticacaoService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<User> user = userRepository.findByEmail(username);
		if (user.isPresent()) {
			System.out.println("AutenticacaoService Achou usuario");
			return user.get();
		}

		throw new UsernameNotFoundException("DADOS INVALIDOS do usuario");

	}

}
