package com.BananaDeployment.CoxinhaApp.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.BananaDeployment.CoxinhaApp.model.Salgado;
import com.BananaDeployment.CoxinhaApp.repository.SalgadosRepository;

@RestController
@RequestMapping("/salgados")
public class ControllerSalgados {

	@Autowired
	private SalgadosRepository salgados;
	
	@GetMapping
	public List<Salgado> listar(){
		return salgados.findAll();		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Salgado> buscar(@PathVariable Long id) {
		Optional<Salgado> salgado = salgados.findById(id);		
		if(!salgado.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(salgado.get());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Salgado adicionar(@Valid @RequestBody Salgado salgado) {
		Optional<Salgado> salgadoExistente = salgados.findByNomeAndValor(salgado.getNome(), salgado.getValor());
		if(salgadoExistente.isPresent())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		return salgados.save(salgado);
	}
	
	@DeleteMapping	
	public HttpStatus deleteSalgadoById(@PathVariable Long id) {
		Optional<Salgado> salgado = salgados.findById(id);
		HttpStatus httpStatus = HttpStatus.OK;
		if(salgado.isPresent()) 
			salgados.delete(salgado.get());
		else
			httpStatus = HttpStatus.NOT_FOUND;
		if(salgado.isPresent())
			httpStatus = HttpStatus.NOT_MODIFIED;
		return httpStatus;
	}
	
	@DeleteMapping	
	public HttpStatus deleteSalgadoByNomeAndValor(@PathVariable String nome, @PathVariable double valor) {
		Optional<Salgado> salgado = salgados.findByNomeAndValor(nome, valor);
		HttpStatus httpStatus = HttpStatus.OK;
		if(salgado.isPresent()) 
			salgados.delete(salgado.get());
		else
			httpStatus = HttpStatus.NOT_FOUND;
		if(salgado.isPresent())
			httpStatus = HttpStatus.NOT_MODIFIED;
		return httpStatus;
	}
}
