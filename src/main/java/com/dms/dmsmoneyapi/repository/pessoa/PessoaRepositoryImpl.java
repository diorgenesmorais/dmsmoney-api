package com.dms.dmsmoneyapi.repository.pessoa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import com.dms.dmsmoneyapi.model.Pessoa;
import com.dms.dmsmoneyapi.model.Pessoa_;

public class PessoaRepositoryImpl implements PessoaRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	/*
	 * (non-Javadoc)
	 * Result: update pessoa set ativo=? where id=?
	 */
	@Transactional
	@Override
	public int atualizarAtivo(Long id, Boolean ativo) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaUpdate<Pessoa> criteria = builder.createCriteriaUpdate(Pessoa.class);
		Root<Pessoa> root = criteria.from(Pessoa.class);

		criteria.set(Pessoa_.ativo, ativo).where(builder.equal(root.<Long>get(Pessoa_.id), id));
		Query query = manager.createQuery(criteria);
		return query.executeUpdate();
	}

}
