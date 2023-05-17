package com.algaworks.ecommerce.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class NotaFiscal {

    @EqualsAndHashCode.Include
    @Id
    private Integer id;

    private Integer pedidoId;

    private String xml;

    private Date dataEmissao;
}
