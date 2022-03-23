package br.com.mercadolivre.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

	private Long productId;
	private String name;
	private String category;
	private String brand;
	private BigDecimal price;
	private Integer quantity;
	private Boolean freeShipping;
	private String prestige;
	
}
