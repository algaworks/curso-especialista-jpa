package com.algaworks.ecommerce.model;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@DiscriminatorValue("boleto")
@Entity
public class PagamentoBoleto extends Pagamento {

    @NotBlank
    @Column(name = "codigo_barras", length = 100)
    private String codigoBarras;

    @NotNull
    @FutureOrPresent
    @Column(name = "data_vencimento")
    private LocalDate dataVencimento;
}
