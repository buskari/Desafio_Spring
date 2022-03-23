package br.com.mercadolivre.dto;

import br.com.mercadolivre.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ResponseProductDTO {
    private Long id;
    private String name;
    private Integer quantity;

    public ResponseProductDTO convertToDTO(Product product) {
        return new ResponseProductDTO(
                product.getProductId(),
                product.getName(),
                product.getQuantity()
        );
    }
}
