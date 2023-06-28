package com.algaworks.ecommerce.consultasnativas;

import com.algaworks.ecommerce.EntityManagerTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;

public class StoredProceduresTest extends EntityManagerTest {

    @Test
    public void usarParametrosInEOut() {
        StoredProcedureQuery storedProcedureQuery = entityManager
                .createStoredProcedureQuery("buscar_nome_produto");

        storedProcedureQuery.registerStoredProcedureParameter(
                "produto_id", Integer.class, ParameterMode.IN);

        storedProcedureQuery.registerStoredProcedureParameter(
                "produto_nome", String.class, ParameterMode.OUT);

        storedProcedureQuery.setParameter("produto_id", 1);

        String nome = (String) storedProcedureQuery
                .getOutputParameterValue("produto_nome");

        Assertions.assertEquals("Kindle", nome);
    }
}
