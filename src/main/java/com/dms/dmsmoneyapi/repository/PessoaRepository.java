package com.dms.dmsmoneyapi.repository;

import com.dms.dmsmoneyapi.model.Pessoa;
import com.dms.dmsmoneyapi.repository.pessoa.PessoaRepositoryQuery;

public interface PessoaRepository extends GenericRepository<Pessoa, Long>, PessoaRepositoryQuery {

}
