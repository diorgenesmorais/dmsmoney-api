package com.dms.dmsmoneyapi.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dms.dmsmoneyapi.event.ResourceCreatedEvent;
import com.dms.dmsmoneyapi.model.Lancamento;
import com.dms.dmsmoneyapi.repository.LancamentoRepository;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

	@Autowired
	private LancamentoRepository lancamentoRepository;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public List<Lancamento> listar() {
		return lancamentoRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Lancamento> buscarPorId(@PathVariable Long id) {
		Lancamento lancamento = lancamentoRepository.findOne(id);
		return lancamento != null ? ResponseEntity.ok(lancamento) : ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Lancamento> nova(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response) {
		Lancamento lancamentoSalvo = lancamentoRepository.save(lancamento);

		publisher.publishEvent(new ResourceCreatedEvent(this, response, lancamentoSalvo.getId()));

		return ResponseEntity.status(HttpStatus.CREATED).body(lancamento);
	}
}
