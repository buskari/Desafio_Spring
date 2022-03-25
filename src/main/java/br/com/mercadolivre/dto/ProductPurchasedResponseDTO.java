package br.com.mercadolivre.dto;

import br.com.mercadolivre.model.Product;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Data
public class ProductPurchasedResponseDTO {

    private Long id = ThreadLocalRandom.current().nextLong(20,999);
    private List<Product> products;
    private BigDecimal total;

}
