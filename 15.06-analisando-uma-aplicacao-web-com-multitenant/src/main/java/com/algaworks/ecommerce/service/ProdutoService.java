package com.algaworks.ecommerce.service;

import com.algaworks.ecommerce.model.Produto;
import com.algaworks.ecommerce.repository.Produtos;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class ProdutoService {

    @Autowired
    private Produtos produtos;

    @Transactional
    public Produto criar(Produto produto) {
        produto.setDataCriacao(LocalDateTime.now());

        return produtos.salvar(produto);
    }

    @Transactional
    public Produto atualizar(Integer id, Map<String, Object> produto) {
        Produto produtoAtual = produtos.buscar(id);

        try {
            BeanUtils.populate(produtoAtual, produto);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        produtoAtual.setDataUltimaAtualizacao(LocalDateTime.now());

        return produtos.salvar(produtoAtual);
    }
}
