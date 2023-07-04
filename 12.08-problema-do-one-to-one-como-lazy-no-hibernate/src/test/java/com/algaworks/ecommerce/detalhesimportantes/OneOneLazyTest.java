package com.algaworks.ecommerce.detalhesimportantes;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class OneOneLazyTest extends EntityManagerTest {

    @Test
    public void mostrarProblema() {
        System.out.println("BUSCANDO UM PEDIDO:");
        Pedido pedido = entityManager
                .createQuery("select p from Pedido p " +
                        "left join fetch p.pagamento " +
                        "left join fetch p.cliente " +
                        "left join fetch p.notaFiscal " +
                        "where p.id = 1", Pedido.class)
                .getSingleResult();
        Assertions.assertNotNull(pedido);

        System.out.println("----------------------------------------------------");

        System.out.println("BUSCANDO UMA LISTA DE PEDIDOS:");
        List<Pedido> lista = entityManager
                .createQuery("select p from Pedido p " +
                        "left join fetch p.pagamento " +
                        "left join fetch p.cliente " +
                        "left join fetch p.notaFiscal", Pedido.class)
                .getResultList();
        Assertions.assertFalse(lista.isEmpty());
    }
}
