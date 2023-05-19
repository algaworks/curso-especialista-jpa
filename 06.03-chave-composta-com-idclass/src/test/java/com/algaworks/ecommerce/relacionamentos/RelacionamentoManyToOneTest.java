package com.algaworks.ecommerce.relacionamentos;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RelacionamentoManyToOneTest extends EntityManagerTest {

    @Test
    public void verificarRelacionamento() {
        Cliente cliente = entityManager.find(Cliente.class, 1);

        Pedido pedido = new Pedido();
        pedido.setStatus(StatusPedido.AGUARDANDO);
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setTotal(BigDecimal.TEN);

        pedido.setCliente(cliente);

        entityManager.getTransaction().begin();
        entityManager.persist(pedido);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
        Assertions.assertNotNull(pedidoVerificacao.getCliente());
    }

    @Test
    public void verificarRelacionamentoItemPedido() {
//        Cliente cliente = entityManager.find(Cliente.class, 1);
//        Produto produto = entityManager.find(Produto.class, 1);
//
//        Pedido pedido = new Pedido();
//        pedido.setStatus(StatusPedido.AGUARDANDO);
//        pedido.setDataCriacao(LocalDateTime.now());
//        pedido.setTotal(BigDecimal.TEN);
//        pedido.setCliente(cliente);
//
//        ItemPedido itemPedido = new ItemPedido();
//        itemPedido.setPrecoProduto(produto.getPreco());
//        itemPedido.setQuantidade(1);
//        itemPedido.setPedido(pedido);
//        itemPedido.setProduto(produto);
//
//        entityManager.getTransaction().begin();
//        entityManager.persist(pedido);
//        entityManager.persist(itemPedido);
//        entityManager.getTransaction().commit();
//
//        entityManager.clear();
//
//        ItemPedido itemPedidoVerificacao = entityManager.find(ItemPedido.class, itemPedido.getId());
//        Assertions.assertNotNull(itemPedidoVerificacao.getPedido());
//        Assertions.assertNotNull(itemPedidoVerificacao.getProduto());
    }
}
