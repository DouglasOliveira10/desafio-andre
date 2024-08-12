package br.com.desafio.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ResponseItems {
	
	private Object items;
	private int pageNumber;
	private int pageSize;
	private long totalSize;
}
