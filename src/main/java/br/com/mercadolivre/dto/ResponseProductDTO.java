package br.com.mercadolivre.dto;

import br.com.mercadolivre.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ResponseProductDTO {
    private Long productId;
    private String name;
    private Integer quantity;

    public static ResponseProductDTO convertToDTO(Product product) {
        return new ResponseProductDTO(
                product.getProductId(),
                product.getName(),
                product.getQuantity()
        );
    }

    public static List<ResponseProductDTO> convertToDTO(List<Product> productList) {
        return productList.stream().map(product -> new ResponseProductDTO(
                product.getProductId(),
                product.getName(),
                product.getQuantity()
        )).collect(Collectors.toList());
    }
}
