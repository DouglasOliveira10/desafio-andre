package br.com.desafio.repository;

import br.com.desafio.model.SondaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SondaRepository extends JpaRepository<SondaEntity, Long>  {
}
