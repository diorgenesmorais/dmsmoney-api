package com.dms.dmsmoneyapi.repository;

import com.dms.dmsmoneyapi.model.Lancamento;
import com.dms.dmsmoneyapi.repository.lancamento.LancamentoRepositoryQuery;

public interface LancamentoRepository extends GenericRepository<Lancamento, Long>, LancamentoRepositoryQuery {

}
