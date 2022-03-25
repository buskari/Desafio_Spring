package br.com.mercadolivre.model;

import br.com.mercadolivre.dto.ProductPurchasedRequestDTO;
import lombok.Data;

import java.util.List;

@Data
public class ProductPurchaseRequest {

    private List<ProductPurchasedRequestDTO> purchasedProducts;
}

