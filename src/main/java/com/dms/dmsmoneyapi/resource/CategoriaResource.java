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
import com.dms.dmsmoneyapi.model.Categoria;
import com.dms.dmsmoneyapi.repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public List<Categoria> listar() {
		return categoriaRepository.findAll();
	}

	/**
	 * Criar uma nova categoria
	 * 
	 * @param categoria
	 *            Json vindo do cliente
	 * @param response
	 *            para setar o cabeçalho (Location no Headers)
	 * @return {@code ResponseEntity} com a categoria salva no body.
	 */
	@PostMapping
	public ResponseEntity<Categoria> nova(@Valid @RequestBody Categoria categoria, HttpServletResponse response) {
		Categoria categoriaSalva = categoriaRepository.save(categoria);

		publisher.publishEvent(new ResourceCreatedEvent(this, response, categoriaSalva.getId()));
		// return: Status, URI, body
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Categoria> buscarId(@PathVariable Long id) {
		Categoria categoria = categoriaRepository.findOne(id);
		return categoria != null ? ResponseEntity.ok(categoria) : ResponseEntity.notFound().build();
	}

}
