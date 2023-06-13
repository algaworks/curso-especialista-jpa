package br.com.agdev.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Filme {

    private String nome;
    private Integer estoque;
    private Double precoLocacao;
}
