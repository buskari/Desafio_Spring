package br.com.mercadolivre.dto;

import java.math.BigDecimal;

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
	private Long productId;
	private String name;
	private String category;
	private String brand;
	private BigDecimal price;
	private Integer quantity;
	private Boolean freeShipping;
	private String prestige;
	
	public static Product productToDTO(RequestProductDTO dto) {
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
	
}
