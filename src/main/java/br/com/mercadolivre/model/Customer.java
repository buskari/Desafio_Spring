package br.com.mercadolivre.model;

import java.math.BigDecimal;

import br.com.mercadolivre.service.States;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    private Long customerId;
    private String name;
    private States state;

}
