package br.com.bsb.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.bsb.model.Rat;

public interface RatRepository extends CrudRepository<Rat, Long> {

}
