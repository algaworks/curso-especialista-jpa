package com.algaworks.ecommerce.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Categoria {

    @EqualsAndHashCode.Include
    @Id
    private Integer id;

    private String nome;

    private Integer categoriaPaiId;
}
