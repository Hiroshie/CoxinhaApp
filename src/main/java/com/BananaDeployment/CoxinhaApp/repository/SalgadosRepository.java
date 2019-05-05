package com.BananaDeployment.CoxinhaApp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BananaDeployment.CoxinhaApp.model.Salgado;

public interface SalgadosRepository extends JpaRepository<Salgado, Long>{

	Optional<Salgado> findByNomeAndValor(String nome, double valor);
}
