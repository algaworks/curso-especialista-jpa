package com.algaworks.ecommerce.repository;

import com.algaworks.ecommerce.model.Produto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class Produtos {

    @PersistenceContext
    private EntityManager entityManager;

    public Produto buscar(Integer id, String tenant) {
        return entityManager
                .createQuery("select p from Produto p where p.id = :id and p.tenant = :tenant",
                        Produto.class)
                .setParameter("id", id)
                .setParameter("tenant", tenant)
                .getSingleResult();
    }

    public Produto salvar(Produto produto) {
        return entityManager.merge(produto);
    }

    public List<Produto> listar(String tenant) {
        return entityManager
                .createQuery("select p from Produto p where p.tenant = :tenant", Produto.class)
                .setParameter("tenant", tenant)
                .getResultList();
    }
}
