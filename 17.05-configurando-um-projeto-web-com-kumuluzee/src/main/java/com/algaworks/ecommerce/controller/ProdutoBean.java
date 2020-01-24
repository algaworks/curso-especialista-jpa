package com.algaworks.ecommerce.controller;

import com.algaworks.ecommerce.model.Produto;
import com.algaworks.ecommerce.repository.Produtos;
import com.algaworks.ecommerce.service.ProdutoService;
import lombok.Getter;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class ProdutoBean implements Serializable {

    @Inject
    private Produtos produtos;

    @Inject
    private ProdutoService produtoService;

    @Getter
    private List<Produto> lista;

    @Getter
    private Produto produto = new Produto();

    public void listar() {
        this.lista = this.produtos.listar();
    }

    public void buscar() {
        this.produto = this.produtos.buscar(this.produto.getId());
    }

    public void salvar() {
        this.produtoService.salvar(this.produto);

        FacesContext.getCurrentInstance().addMessage(
                null, new FacesMessage("Produto salvo com sucesso!"));
    }
}
