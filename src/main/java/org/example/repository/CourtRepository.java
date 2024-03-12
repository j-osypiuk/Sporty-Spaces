package org.example.repository;

import jakarta.persistence.*;
import org.example.entity.Court;

import java.util.List;

public class CourtRepository implements Repository<Court> {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("myapp");
    private final EntityManager em = emf.createEntityManager();

    @Override
    public boolean add(Court obj) {
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
            Court court = em.find(Court.class, id);
            em.remove(court);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public Court find(int id) {
        try {
            em.getTransaction().begin();
            Court court = em.find(Court.class, id);
            em.getTransaction().commit();
            return court;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean update(Court court) {
        try {
            em.getTransaction().begin();
            em.merge(court);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public List<Court> findAll() {
        try{
            em.getTransaction().begin();
            Query query = em.createQuery("select c from Court c");
            List<Court> courts = query.getResultList();
            em.getTransaction().commit();
            return courts;
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
