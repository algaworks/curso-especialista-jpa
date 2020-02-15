package com.algaworks.ecommerce.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Estoque {

    @EqualsAndHashCode.Include
    @Id
    private Integer id;

    private Integer produtoId;

    private Integer quantidade;
}
