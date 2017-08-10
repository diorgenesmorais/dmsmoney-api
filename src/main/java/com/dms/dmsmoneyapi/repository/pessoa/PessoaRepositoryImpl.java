package com.dms.dmsmoneyapi.repository.pessoa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.dao.EmptyResultDataAccessException;

import com.dms.dmsmoneyapi.model.Pessoa;

/**
 * TODO: quando possível implementar Metamodel nesta
 * {@code PessoaRepositoryImpl}
 * 
 * @author Diorgenes Morais
 * @version 1.0.1
 */
public class PessoaRepositoryImpl implements PessoaRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	/*
	 * (non-Javadoc) Result: update pessoa set ativo=? where id=?
	 */
	@Transactional
	@Override
	public void atualizarAtivo(Long id, Boolean ativo) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaUpdate<Pessoa> criteria = builder.createCriteriaUpdate(Pessoa.class);
		Root<Pessoa> root = criteria.from(Pessoa.class);

		criteria.set("ativo", ativo).where(builder.equal(root.<Long>get("id"), id));
		Query query = manager.createQuery(criteria);
		if (query.executeUpdate() != 1) {
			throw new EmptyResultDataAccessException(1);
		}
	}

}
