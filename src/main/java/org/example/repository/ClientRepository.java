package org.example.repository;

import jakarta.persistence.*;
import org.example.entity.Client;

import java.util.List;

public class ClientRepository implements Repository<Client> {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("myapp");
    private final EntityManager em = emf.createEntityManager();

    @Override
    public boolean add(Client obj) {
        try {
            em.getTransaction().begin();
            em.persist(obj);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public boolean remove(int id) {
        try {
            em.getTransaction().begin();
            Client client = em.find(Client.class, id);
            em.remove(client);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public Client find(int id) {
        try {
            em.getTransaction().begin();
            Client client = em.find(Client.class, id);
            em.getTransaction().commit();
            return client;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean update(Client client) {
        try {
            em.getTransaction().begin();
            em.merge(client);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        }

    }

    @Override
    public List<Client> findAll() {
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("select c from Client c");
            List<Client> clients = query.getResultList();
            em.getTransaction().commit();
            return clients;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void close() throws IllegalStateException {
        emf.close();
        em.close();
    }
}
