package com.algaworks.ecommerce;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import jakarta.persistence.EntityManager;

public class EntityManagerTest extends EntityManagerFactoryTest {

    protected EntityManager entityManager;

    @BeforeEach
    public void setUp() {
        entityManager = entityManagerFactory.createEntityManager();
    }

    @AfterEach
    public void tearDown() {
        entityManager.close();
    }
}
