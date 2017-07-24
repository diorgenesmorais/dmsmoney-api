package com.dms.dmsmoneyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dms.dmsmoneyapi.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
