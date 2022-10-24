package br.org.com.recode.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.com.recode.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	Optional<Cliente> findByEmail(String email);
	Cliente findByNome(String nomeCliente);
}
