package com.dms.dmsmoneyapi.repository.lancamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dms.dmsmoneyapi.model.Lancamento;
import com.dms.dmsmoneyapi.repository.filter.LancamentoFilter;
import com.dms.dmsmoneyapi.repository.projection.ResumoLancamento;

public interface LancamentoRepositoryQuery {

	public Page<Lancamento> filter(LancamentoFilter lancamentoFilter, Pageable pageable);
	
	public Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable);
}
