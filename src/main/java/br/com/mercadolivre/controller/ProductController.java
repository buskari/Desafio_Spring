package br.com.mercadolivre.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.mercadolivre.dto.RequestListProductDTO;
import br.com.mercadolivre.dto.RequestProductDTO;
import br.com.mercadolivre.dto.ResponseProductDTO;
import br.com.mercadolivre.model.Product;
import br.com.mercadolivre.model.ProductPurchaseRequest;
import br.com.mercadolivre.model.ProductPurchaseResponse;
import br.com.mercadolivre.service.ProductNotNullValidation;
import br.com.mercadolivre.service.ProductService;
import br.com.mercadolivre.service.ProductValidatePrice;
import br.com.mercadolivre.service.ProductValidateQuantity;

@RestController
public class ProductController {

    @Autowired
    public ProductService service;
    private List<ResponseProductDTO> responseProductDTOList;

    /**
     * Cria uma lista de produtos e adiciona a lista existente
     *
     * @param products - objeto do tipo RequestListProductDTO com uma lista de produtos
     * @return lista de produtos cadastrados
     * @throws IOException exceção no caso de não conseguir ler a lista json
     */
    @PostMapping("/insert-products-request")
    public ResponseEntity<List<ResponseProductDTO>> create(@RequestBody RequestListProductDTO products) throws IOException {
        List<Product> collect = products
                .getProducts()
                .stream()
                .map(RequestProductDTO::dtoToProduct)
                .collect(Collectors.toList());

        service.create(collect, Arrays.asList(
                new ProductValidatePrice(collect),
                new ProductValidateQuantity(collect),
                new ProductNotNullValidation(collect)
        ));
        responseProductDTOList = ResponseProductDTO.convertToDTO(collect);
        return new ResponseEntity<>(responseProductDTOList, HttpStatus.CREATED);
    }

    /**
     * Busca produtos conforme o filtro selecionado ou todos os produtos caso não haja filtro
     *
     * @param category     String - categoria do produto
     * @param name         String - nome do produto
     * @param brand        String - marca do produto
     * @param price        BigDecimal - preço do produto
     * @param quantity     Integer - quantidade do produto
     * @param freeShipping String - frete grátis referente ao produto
     * @param prestige     String - avaliação do produto
     * @param order        Integer - Parametro para ordenação, valores válidos: 0 - Alfabético crescente, 1 - Alfabético decrescente, 2 - Maior a menor preço ou 3 - Menor a maior preço
     * @return Lista de produtos conforme o filtro selecionado
     */
    @GetMapping("/products")
    public ResponseEntity<List<ResponseProductDTO>> products(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) BigDecimal price,
            @RequestParam(required = false) Integer quantity,
            @RequestParam(required = false) String freeShipping,
            @RequestParam(required = false) String prestige,
            @RequestParam(required = false) Integer order
    ) throws IOException {
        List<Product> result = service.findProducts(
                category,
                name,
                brand,
                price,
                quantity,
                freeShipping,
                prestige,
                order
        );

        responseProductDTOList = ResponseProductDTO.convertToDTO(result);
        return ResponseEntity.ok(responseProductDTOList);
    }

    /**
     * Pedido de compra
     *
     * @param products - objeto do tipo ProductPurchaseRequest com uma lista de produtos
     * @return lista de produtos da venda
     * @throws Exception exceção no caso de não conseguir ler a lista json estoque
     */
    @PostMapping("/purchase-request")
    public ResponseEntity<ProductPurchaseResponse> purchaseRequest(@RequestBody ProductPurchaseRequest products) throws Exception {
        return ResponseEntity.ok(service.newPurchase(products));
    }
}
