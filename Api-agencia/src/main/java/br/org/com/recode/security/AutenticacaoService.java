package br.org.com.recode.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.org.com.recode.model.Cliente;
import br.org.com.recode.repository.ClienteRepository;

@Service
public class AutenticacaoService implements UserDetailsService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<Cliente> cliente = clienteRepository.findByEmail(username);
		if (cliente.isPresent()) {
			System.out.println("AutenticacaoService Achou");
			return cliente.get();
		}

		throw new UsernameNotFoundException("DADOS INVALIDOS");

	}

}
