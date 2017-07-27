package com.dms.dmsmoneyapi.repository.lancamento;

import java.util.List;

import com.dms.dmsmoneyapi.model.Lancamento;
import com.dms.dmsmoneyapi.repository.filter.LancamentoFilter;

public interface LancamentoRepositoryQuery {

	public List<Lancamento> filter(LancamentoFilter lancamentoFilter);
}
