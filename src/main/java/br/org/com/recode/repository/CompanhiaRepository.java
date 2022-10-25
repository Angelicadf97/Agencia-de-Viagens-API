package br.org.com.recode.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.com.recode.model.Companhia;

public interface CompanhiaRepository  extends JpaRepository<Companhia, Long>{

}
