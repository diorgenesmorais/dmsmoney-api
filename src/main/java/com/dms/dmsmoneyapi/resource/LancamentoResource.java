package com.dms.dmsmoneyapi.resource;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dms.dmsmoneyapi.event.ResourceCreatedEvent;
import com.dms.dmsmoneyapi.excetionhandler.ErrorDetails;
import com.dms.dmsmoneyapi.excetionhandler.ErrorDetailsBuilder;
import com.dms.dmsmoneyapi.model.Lancamento;
import com.dms.dmsmoneyapi.repository.LancamentoRepository;
import com.dms.dmsmoneyapi.repository.filter.LancamentoFilter;
import com.dms.dmsmoneyapi.repository.projection.ResumoLancamento;
import com.dms.dmsmoneyapi.service.LancamentoService;
import com.dms.dmsmoneyapi.service.exception.PessoaInexistenteOuInativaException;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

	@Autowired
	private LancamentoRepository lancamentoRepository;

	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private LancamentoService lancamentoService;

	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
	public Page<Lancamento> pesquisar(LancamentoFilter lancamentoFilter, Pageable pageable) {
		return lancamentoRepository.filter(lancamentoFilter, pageable);
	}

	@GetMapping(params = "resumo")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
	public Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable) {
		return lancamentoRepository.resumir(lancamentoFilter, pageable);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
	public ResponseEntity<Lancamento> buscarPorId(@PathVariable Long id) {
		Lancamento lancamento = lancamentoRepository.findOne(id);
		return lancamento != null ? ResponseEntity.ok(lancamento) : ResponseEntity.notFound().build();
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_LANCAMENTO') and #oauth2.hasScope('write')")
	public ResponseEntity<Lancamento> nova(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response) {
		Lancamento lancamentoSalvo = lancamentoService.salvar(lancamento);

		publisher.publishEvent(new ResourceCreatedEvent(this, response, lancamentoSalvo.getId()));

		return ResponseEntity.status(HttpStatus.CREATED).body(lancamento);
	}
	
	@ExceptionHandler({ PessoaInexistenteOuInativaException.class })
	public ResponseEntity<Object> handlePessoaInexistenteOuInativa(PessoaInexistenteOuInativaException ex){
		List<ErrorDetails> erros = Arrays.asList(ErrorDetailsBuilder.newBuilder()
				.status(HttpStatus.BAD_REQUEST.value())
				.timestamp(new Date().getTime())
				.userMessage("Pessoa não informada ou inativa")
				.developerMessage(ex.toString())
				.build());
		return ResponseEntity.badRequest().body(erros);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_LANCAMENTO') and #oauth2.hasScope('write')")
	public void remover(@PathVariable Long id) {
		lancamentoRepository.delete(id);
	}
}
