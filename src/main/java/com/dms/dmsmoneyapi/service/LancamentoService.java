package com.dms.dmsmoneyapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dms.dmsmoneyapi.model.Lancamento;
import com.dms.dmsmoneyapi.model.Pessoa;
import com.dms.dmsmoneyapi.repository.LancamentoRepository;
import com.dms.dmsmoneyapi.repository.PessoaRepository;
import com.dms.dmsmoneyapi.service.exception.PessoaInexistenteOuInativaException;

@Service
public class LancamentoService {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private LancamentoRepository lancamentoRepository;

	public Lancamento salvar(Lancamento lancamento) {
		validarPessoa(lancamento);
		return lancamentoRepository.save(lancamento);
	}

	/**
	 * 
	 * @param id
	 * @param lancamento
	 * @return {@code Lancamento}
	 * {@code EmptyResultDataAccessException} or {@code PessoaInexistenteOuInativaException}
	 */
	public Lancamento atualizar(Long id, Lancamento lancamento) {
		Lancamento lancamentoSalvo = lancamentoRepository.findById(id);
		
		validarPessoa(lancamento);
			
		BeanUtils.copyProperties(lancamento, lancamentoSalvo, "id");

		return lancamentoRepository.save(lancamentoSalvo);
	}

	private void validarPessoa(Lancamento lancamento) {
		Pessoa pessoa = null;

		if (lancamento.getPessoa().getId() != null) {
			pessoa = pessoaRepository.findOne(lancamento.getPessoa().getId());
		}

		if (pessoa == null || pessoa.isInativo()) {
			throw new PessoaInexistenteOuInativaException();
		}
	}

}
