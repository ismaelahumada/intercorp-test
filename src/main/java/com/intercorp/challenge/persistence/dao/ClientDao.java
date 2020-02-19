package com.intercorp.challenge.persistence.dao;

import com.intercorp.challenge.persistence.entity.Client;
import com.intercorp.challenge.persistence.entity.Client_;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Component
public class ClientDao<T> implements IClientDao<Client> {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<Client> get(long id) {
        return Optional.ofNullable(em.find(Client.class, id));
    }

    @Override
    public List<Client> getAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Client> cq = cb.createQuery(Client.class);
        Root<Client> rootEntry = cq.from(Client.class);
        CriteriaQuery<Client> all = cq.select(rootEntry);
        TypedQuery<Client> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    @Transactional
    public Optional<Client> save(Client client) {
        em.persist(client);
        return get(client.getId());
    }

    @Override
    public void delete(Client p) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaDelete<Client> delete = cb.createCriteriaDelete(Client.class);
        Root<Client> root = delete.from(Client.class);
        delete.where(root.get(Client_.ID));
        em.createQuery(delete).executeUpdate();
    }

    @Override
    public Double getAgeAverage() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Double> query = cb.createQuery(Double.class);
        Root<Client> root = query.from(Client.class);
        query.select(cb.avg(root.get(Client_.AGE)));
        return em.createQuery(query).getSingleResult();
    }

    @Override
    public List<Integer> getAllAges() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Integer> query = cb.createQuery(Integer.class);
        Root<Client> root = query.from(Client.class);
        query.select(root.get(Client_.AGE));
        return em.createQuery(query).getResultList();
    }
}
