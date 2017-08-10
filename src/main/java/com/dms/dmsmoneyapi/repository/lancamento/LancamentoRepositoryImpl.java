package com.dms.dmsmoneyapi.repository.lancamento;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.criterion.MatchMode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.dms.dmsmoneyapi.model.Lancamento;
import com.dms.dmsmoneyapi.repository.filter.LancamentoFilter;
import com.dms.dmsmoneyapi.repository.projection.ResumoLancamento;

/**
 * TODO: quando for possível implementar Metamodel nesta
 * {@code LancamentoRepositoryImpl}
 * 
 * @author Diorgenes Morais
 * @version 1.0.1
 */
public class LancamentoRepositoryImpl implements LancamentoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Lancamento> filter(LancamentoFilter lancamentoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Lancamento> criteria = builder.createQuery(Lancamento.class);
		Root<Lancamento> root = criteria.from(Lancamento.class);

		Predicate[] predicates = createPredicates(lancamentoFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<Lancamento> query = manager.createQuery(criteria);
		addRestrictionPagination(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable, totalRows(lancamentoFilter));
	}

	@Override
	public Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ResumoLancamento> criteria = builder.createQuery(ResumoLancamento.class);
		Root<Lancamento> root = criteria.from(Lancamento.class);

		criteria.select(builder.construct(ResumoLancamento.class, root.get("id"), root.get("descricao"),
				root.get("dataVencimento"), root.get("dataPagto"), root.get("valor"), root.get("tipo"),
				root.get("categoria").get("nome"), root.get("pessoa").get("nome")));

		Predicate[] predicates = createPredicates(lancamentoFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<ResumoLancamento> query = manager.createQuery(criteria);
		addRestrictionPagination(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable, totalRows(lancamentoFilter));
	}

	private void addRestrictionPagination(TypedQuery<?> query, Pageable pageable) {
		int pageNumber = pageable.getPageNumber();
		int maxResult = pageable.getPageSize();
		int firstResult = pageNumber * maxResult;
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResult);
	}

	private Long totalRows(LancamentoFilter lancamentoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Lancamento> root = criteria.from(Lancamento.class);

		Predicate[] predicates = createPredicates(lancamentoFilter, builder, root);
		criteria.where(predicates);

		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

	private Predicate[] createPredicates(LancamentoFilter lancamentoFilter, CriteriaBuilder builder,
			Root<Lancamento> root) {
		List<Predicate> predicates = new ArrayList<>();

		if (!StringUtils.isEmpty(lancamentoFilter.getDescricao())) {
			predicates.add(builder.like(builder.lower(root.get("descricao")),
					MatchMode.ANYWHERE.toMatchString(lancamentoFilter.getDescricao().toLowerCase())));
		}

		if (lancamentoFilter.getDataVencimentoDe() != null) {
			predicates.add(
					builder.greaterThanOrEqualTo(root.get("dataVencimento"), lancamentoFilter.getDataVencimentoDe()));
		}

		if (lancamentoFilter.getDataVencimentoAte() != null) {
			predicates.add(
					builder.lessThanOrEqualTo(root.get("dataVencimento"), lancamentoFilter.getDataVencimentoAte()));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
