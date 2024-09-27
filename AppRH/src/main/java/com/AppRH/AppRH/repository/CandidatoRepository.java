package com.AppRH.AppRH.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.AppRH.AppRH.models.Candidato;
import com.AppRH.AppRH.models.Vaga;

public interface CandidatoRepository extends CrudRepository<Candidato,String>{

	Iterable<Candidato> FindByVaga(Vaga vaga);
	
	Candidato FindByCpf(String cpf);
	
	Candidato FindById(long id);
	
	List<Candidato> FindByNomeCandidato(String nomeCandidato);
}
