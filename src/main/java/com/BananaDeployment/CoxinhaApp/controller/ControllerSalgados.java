package com.BananaDeployment.CoxinhaApp.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
		return salgados.save(salgado);
	}
	
	
}
