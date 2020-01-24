package com.algaworks.ecommerce.service;

import com.algaworks.ecommerce.model.Produto;
import com.algaworks.ecommerce.repository.Produtos;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.time.LocalDateTime;

public class ProdutoService implements Serializable {

    @Inject
    private Produtos produtos;

    @Transactional
    public Produto salvar(Produto produto) {
        if (produto.getId() == null) {
            produto.setDataCriacao(LocalDateTime.now());
        } else {
            produto.setDataUltimaAtualizacao(LocalDateTime.now());
        }

        return produtos.salvar(produto);
    }
}
