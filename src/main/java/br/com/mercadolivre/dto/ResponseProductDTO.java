package br.com.mercadolivre.dto;

import br.com.mercadolivre.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ProductDTO {
    private Integer id;
    private String name;
    private Integer quantity;

    public ProductDTO convertToDTO(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getQuantity()
        );
    }
}
