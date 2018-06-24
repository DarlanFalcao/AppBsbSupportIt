package br.com.bsb.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.bsb.model.Rat;
import br.com.bsb.model.Tecnico;

public interface RatRepository extends CrudRepository<Rat, Long> {

	public List<Rat> findByTecnico(Tecnico idTecnico);
	
}
