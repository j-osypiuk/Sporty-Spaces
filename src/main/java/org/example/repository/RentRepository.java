package org.example.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import org.example.entity.Rent;

import java.util.List;

public class RentRepository implements Repository<Rent>{

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("myapp");
    private final EntityManager em = emf.createEntityManager();

    @Override
    public boolean add(Rent rent) {
        try {
            em.getTransaction().begin();
            em.merge(rent.getClient());
            em.merge(rent.getCourt());
            em.persist(rent);
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
            Rent rent = em.find(Rent.class, id);
            em.remove(rent);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public Rent find(int id) {
        try {
            em.getTransaction().begin();
            Rent rent = em.find(Rent.class, id);
            em.refresh(rent);
            em.getTransaction().commit();
            return rent;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean update(Rent rent) {
        try {
            em.getTransaction().begin();
            em.merge(rent.getCourt());
            em.merge(rent.getClient());
            em.merge(rent);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public List<Rent> findAll() {
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("select r from Rent r");
            List<Rent> rents = query.getResultList();
            em.getTransaction().commit();
            return rents;
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
