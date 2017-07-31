package com.dms.dmsmoneyapi.repository.pessoa;

public interface PessoaRepositoryQuery {

	/**
	 * Atualizar o atributo {@code Pessoa_.ativo}
	 * 
	 * @param id
	 *            de {@code Pessoa}
	 * @param ativo
	 *            equals {@code true}, {@code false} for inactive.
	 * @return 1 if ok
	 */
	public void atualizarAtivo(Long id, Boolean ativo);
}
