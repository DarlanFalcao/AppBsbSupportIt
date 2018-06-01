package br.com.bsb.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.bsb.model.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {

}
