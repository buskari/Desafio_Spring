package br.com.mercadolivre.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Product {

	private Integer id;
	private String name;
	private String category;
	private String brand;
	private BigDecimal price;
	private Integer quantity;
	private Boolean freeShipping;
	private String prestige;
	
}
