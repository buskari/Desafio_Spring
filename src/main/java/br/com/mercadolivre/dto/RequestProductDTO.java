package br.com.mercadolivre.dto;

import java.math.BigDecimal;

import javax.validation.Valid;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.mercadolivre.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Component
@NoArgsConstructor
@AllArgsConstructor
public class RequestProductDTO {
	private Long productId;
	private String name;
	private Integer quantity;
	private String category;
	private String brand;
	private BigDecimal price;
	private Boolean freeShipping;
	private String prestige;

	public static Product dtoToProduct(@Valid RequestProductDTO dto) {
		return Product
				.builder()
				.productId(dto.getProductId())
				.name(dto.getName())
				.category(dto.getCategory())
				.brand(dto.getBrand())
				.price(dto.getPrice())
				.quantity(dto.getQuantity())
				.freeShipping(dto.getFreeShipping())
				.prestige(dto.getPrestige())
				.build();
	}

	public static RequestProductDTO productToDTO(@Valid Product product) {
		return RequestProductDTO
				.builder()
				.productId(product.getProductId())
				.name(product.getName())
				.category(product.getCategory())
				.brand(product.getBrand())
				.price(product.getPrice())
				.quantity(product.getQuantity())
				.freeShipping(product.getFreeShipping())
				.prestige(product.getPrestige())
				.build();
	}
}