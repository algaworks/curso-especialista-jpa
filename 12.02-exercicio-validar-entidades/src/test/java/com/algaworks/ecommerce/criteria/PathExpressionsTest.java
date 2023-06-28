package com.algaworks.ecommerce.criteria;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import java.util.List;

public class PathExpressionsTest extends EntityManagerTest {

    @Test
    public void buscarPedidosComProdutoDeIDIgual1Exercicio() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class);
        Root<Pedido> root = criteriaQuery.from(Pedido.class);
        Join<Pedido, ItemPedido> joinItemPedido = root.join(Pedido_.itens);

        criteriaQuery.select(root);

        criteriaQuery.where(
                criteriaBuilder.equal(
                        joinItemPedido.get(ItemPedido_.produto).get(Produto_.id), 1));

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Pedido> lista = typedQuery.getResultList();

        Assertions.assertFalse(lista.isEmpty());
    }

    @Test
    public void usarPathExpression() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class);
        Root<Pedido> root = criteriaQuery.from(Pedido.class);

        criteriaQuery.select(root);

        criteriaQuery.where(
                criteriaBuilder.like(root.get(Pedido_.cliente).get(Cliente_.nome), "M%"));

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Pedido> lista = typedQuery.getResultList();

        Assertions.assertFalse(lista.isEmpty());
    }
}
