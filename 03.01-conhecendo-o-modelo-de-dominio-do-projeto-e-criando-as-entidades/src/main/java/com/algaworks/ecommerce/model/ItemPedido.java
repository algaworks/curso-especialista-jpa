package com.algaworks.ecommerce.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class ItemPedido {

    @EqualsAndHashCode.Include
    @Id
    private Integer id;

    private Integer pedidoId;

    private Integer produtoId;

    private BigDecimal precoProduto;

    private Integer quantidade;
}
