package br.com.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.desafio.model.PlanetaEntity;

public interface PlanetaRepository extends JpaRepository<PlanetaEntity, Long> {

}
