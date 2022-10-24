package br.org.com.recode.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.com.recode.model.Cliente;
import br.org.com.recode.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByEmail(String email);
	User findByNome(String nomeUser);
}
