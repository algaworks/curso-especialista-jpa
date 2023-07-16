package com.algaworks.ecommerce.detalhesimportantes;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Cliente_;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Pedido_;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.Subgraph;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class EntityGraphTest extends EntityManagerTest {

    @Test
    public void buscarAtributosEssenciaisComNamedEntityGraph() {
        EntityGraph<?> entityGraph = entityManager
                .createEntityGraph("Pedido.dadosEssencias");
        entityGraph.addAttributeNodes("pagamento");

        TypedQuery<Pedido> typedQuery = entityManager
                .createQuery("select p from Pedido p", Pedido.class);
        typedQuery.setHint("jakarta.persistence.fetchgraph", entityGraph);
        List<Pedido> lista = typedQuery.getResultList();
        Assertions.assertFalse(lista.isEmpty());
    }

    @Test
    public void buscarAtributosEssenciaisDePedido03() {
        EntityGraph<Pedido> entityGraph = entityManager.createEntityGraph(Pedido.class);
        entityGraph.addAttributeNodes(
                Pedido_.dataCriacao, Pedido_.status, Pedido_.total);

        Subgraph<Cliente> subgraphCliente = entityGraph
                .addSubgraph(Pedido_.cliente);
        subgraphCliente.addAttributeNodes(Cliente_.nome, Cliente_.cpf);

        TypedQuery<Pedido> typedQuery = entityManager
                .createQuery("select p from Pedido p", Pedido.class);
        typedQuery.setHint("jakarta.persistence.fetchgraph", entityGraph);
        List<Pedido> lista = typedQuery.getResultList();
        Assertions.assertFalse(lista.isEmpty());
    }

    @Test
    public void buscarAtributosEssenciaisDePedido02() {
        EntityGraph<Pedido> entityGraph = entityManager.createEntityGraph(Pedido.class);
        entityGraph.addAttributeNodes(
                "dataCriacao", "status", "total");

        Subgraph<Cliente> subgraphCliente = entityGraph
                .addSubgraph("cliente", Cliente.class);
        subgraphCliente.addAttributeNodes("nome", "cpf");

        TypedQuery<Pedido> typedQuery = entityManager
                .createQuery("select p from Pedido p", Pedido.class);
        typedQuery.setHint("jakarta.persistence.fetchgraph", entityGraph);
        List<Pedido> lista = typedQuery.getResultList();
        Assertions.assertFalse(lista.isEmpty());
    }

    @Test
    public void buscarAtributosEssenciaisDePedido() {
        EntityGraph<Pedido> entityGraph = entityManager.createEntityGraph(Pedido.class);
        entityGraph.addAttributeNodes(
                "dataCriacao", "status", "total", "notaFiscal");
        /*
        Map<String, Object> properties = new HashMap<>();
        properties.put("jakarta.persistence.fetchgraph", entityGraph);
//        properties.put("jakarta.persistence.loadgraph", entityGraph);
        Pedido pedido = entityManager.find(Pedido.class, 1, properties);
        Assertions.assertNotNull(pedido);
        */

        TypedQuery<Pedido> typedQuery = entityManager
                .createQuery("select p from Pedido p", Pedido.class);
        typedQuery.setHint("jakarta.persistence.fetchgraph", entityGraph);
        List<Pedido> lista = typedQuery.getResultList();
        Assertions.assertFalse(lista.isEmpty());
    }
}
