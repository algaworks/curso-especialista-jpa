package com.algaworks.ecommerce;

import org.junit.After;
import org.junit.Before;

import javax.persistence.EntityManager;

public class EntityManagerTest extends EntityManagerFactoryTest {

    protected EntityManager entityManager;

    @Before
    public void setUp() {
        entityManager = entityManagerFactory.createEntityManager();
    }

    @After
    public void tearDown() {
        entityManager.close();
    }
}
