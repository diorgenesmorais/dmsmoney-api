package com.dms.dmsmoneyapi.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dms.dmsmoneyapi.model.Lancamento;
import com.dms.dmsmoneyapi.repository.LancamentoRepository;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

	@Autowired
	private LancamentoRepository lancamentoRepository;

	@GetMapping
	public List<Lancamento> listar() {
		return lancamentoRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Lancamento> buscarPorId(@PathVariable Long id) {
		Lancamento lancamento = lancamentoRepository.findOne(id);
		return lancamento != null ? ResponseEntity.ok(lancamento) : ResponseEntity.notFound().build();
	}
}
