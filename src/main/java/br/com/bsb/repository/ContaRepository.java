package br.com.bsb.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.bsb.model.Conta;

public interface ContaRepository extends CrudRepository<Conta, Long> {

}
