package com.dms.dmsmoneyapi.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dms.dmsmoneyapi.model.Pessoa;
import com.dms.dmsmoneyapi.repository.PessoaRepository;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

	@Autowired
	private PessoaRepository pessoaRepository;

	@GetMapping
	public List<Pessoa> listar() {
		return pessoaRepository.findAll();
	}

	@PostMapping
	public ResponseEntity<Pessoa> nova(@RequestBody Pessoa pessoa, HttpServletResponse response) {
		Pessoa pessoaSalva = pessoaRepository.save(pessoa);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(pessoaSalva.getId())
				.toUri();
		response.setHeader("Location", uri.toASCIIString());

		return ResponseEntity.created(uri).body(pessoaSalva);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pessoa> buscarPorId(@PathVariable Long id) {
		Pessoa pessoa = pessoaRepository.findOne(id);
		return (pessoa != null) ? ResponseEntity.ok(pessoa) : ResponseEntity.notFound().build();
	}
}
