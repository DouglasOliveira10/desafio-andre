package br.com.desafio.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "planeta")
public class PlanetaEntity {
	
	@Id
	@SequenceGenerator(name="planeta_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "planeta_seq")
	private Long id;

	private String nome;

	private int maxX;

	private int maxY;

	private boolean permiteBordaInfinita;

}
