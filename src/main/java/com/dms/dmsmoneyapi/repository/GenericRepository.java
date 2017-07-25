package com.dms.dmsmoneyapi.repository;

import java.io.Serializable;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * {@code GenericRepository} is an interface with standard methods
 * 
 * @author Diorgenes Morais
 * @version 1.0.0
 */
@NoRepositoryBean
public interface GenericRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

	/**
	 * Este método retorna a busca por id.
	 * 
	 * @param id
	 * @return an object {@code T}
	 * @throws EmptyResultDataAccessException
	 */
	public default T findById(ID id) throws EmptyResultDataAccessException {
		T t = findOne(id);
		if (t == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return t;
	}
}
