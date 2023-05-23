package com.algaworks.ecommerce.model;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "estoque")
public class Estoque extends EntidadeBaseInteger {

    @OneToOne(optional = false)
    @JoinColumn(name = "produto_id")
    private Produto produto;

    private Integer quantidade;
}
