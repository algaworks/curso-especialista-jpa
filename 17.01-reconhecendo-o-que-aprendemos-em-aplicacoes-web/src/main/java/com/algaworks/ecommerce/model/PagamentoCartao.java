package com.algaworks.ecommerce.model;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;

@Getter
@Setter
@DiscriminatorValue("cartao")
@Entity
public class PagamentoCartao extends Pagamento {

    @NotEmpty
    @Column(name = "numero_cartao", length = 50)
    private String numeroCartao;
}
