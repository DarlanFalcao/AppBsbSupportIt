package br.com.bsb.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.bsb.model.Funcionario;

public interface FuncionarioRepository extends CrudRepository<Funcionario, Long >{

}
