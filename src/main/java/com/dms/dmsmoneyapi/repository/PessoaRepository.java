package com.dms.dmsmoneyapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dms.dmsmoneyapi.model.Pessoa;
import com.dms.dmsmoneyapi.repository.pessoa.PessoaRepositoryQuery;

public interface PessoaRepository extends GenericRepository<Pessoa, Long>, PessoaRepositoryQuery {

	public Page<Pessoa> findByNomeContaining(String nome, Pageable pageable);
}
