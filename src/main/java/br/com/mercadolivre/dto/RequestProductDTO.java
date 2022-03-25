package br.com.mercadolivre.dto;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import br.com.mercadolivre.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestProductDTO {
	@NotNull
	private Long productId;
	@NotNull
	private String name;
	@NotNull
	private Integer quantity;
	@NotNull
	private String category;
	@NotNull
	private String brand;
	@NotNull
	private BigDecimal price;
	@NotNull
	private Boolean freeShipping;
	@NotNull
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