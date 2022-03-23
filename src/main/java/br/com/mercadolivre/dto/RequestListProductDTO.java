package br.com.mercadolivre.dto;

import java.util.List;

import lombok.Data;

@Data
public class RequestListProductDTO {
	private List<RequestProductDTO> products;
}
