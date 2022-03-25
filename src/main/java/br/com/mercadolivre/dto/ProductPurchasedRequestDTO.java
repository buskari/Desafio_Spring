package br.com.mercadolivre.dto;

import br.com.mercadolivre.model.Product;
import lombok.Data;

@Data
public class ProductPurchasedRequestDTO {

    private Long productId;
    private String name;
    private String brand;
    private Integer quantity;

    public ProductPurchasedRequestDTO converte(Product product) {
        this.productId = product.getProductId();
        this.name = product.getName();
        this.brand = product.getBrand();
        this.quantity = product.getQuantity();
        return this;
    }
}
