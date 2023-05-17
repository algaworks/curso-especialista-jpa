package com.algaworks.ecommerce.model;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Getter
@Setter
@Embeddable
public class EnderecoEntregaPedido {

    private String cep;

    private String logradouro;

    private String numero;

    private String complemento;

    private String bairro;

    private String cidade;

    private String estado;
}
