import jakarta.persistence.*;
import org.example.entity.*;
import org.example.manager.RentManager;
import org.example.repository.ClientRepository;
import org.example.repository.CourtRepository;
import org.example.repository.RentRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDateTime;

public class AppTest {

    Client c1 = new Client("David","Groove","123");
    Client c2 = new Client("Steve","Tomphsom","456");
    Client c3 = new Client("Alan","Dobes","127");

    Court co1 = new FootballCourt(25.5,12.3,3.25,2.25);
    Court co2 = new BasketballCourt(22.4,21.5,2.8,1.1);
    Court co3 = new VolleyballCourt(15.2,13.2,1.25,15.0);

    @Test
    public void rentLockTest() {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("myapp");
             EntityManager firstManager = emf.createEntityManager();
             EntityManager secondManager = emf.createEntityManager()) {

            firstManager.getTransaction().begin();
                 firstManager.persist(co1);
                 firstManager.persist(c1);
            firstManager.getTransaction().commit();

            firstManager.getTransaction().begin();
            secondManager.getTransaction().begin();

                Court court1 = firstManager.find(Court.class, 1);
                court1.setRented(true);

                Court court2 = secondManager.find(Court.class, 1);
                court2.setRented(true);

                Rent rent1 = new Rent(court1, c1, LocalDateTime.now());
                Rent rent2 = new Rent(court2, c1, LocalDateTime.now());

                firstManager.persist(rent1);
                secondManager.persist(rent2);

            Assertions.assertDoesNotThrow(() -> firstManager.getTransaction().commit());
            RollbackException rbe = Assert.assertThrows(RollbackException.class, () -> secondManager.getTransaction().commit());
            Assert.assertTrue(rbe.getCause() instanceof OptimisticLockException);

        }
    }

    @Test
    public void businessLogicTest() {
        try (RentManager rentManager = new RentManager(new RentRepository());
             CourtRepository CourtRepo = new CourtRepository();
             ClientRepository ClientRepo = new ClientRepository()) {

            Rent rent = new Rent(co1, c1, LocalDateTime.now());
            Rent forbiddenRent1 = new Rent(co2, c1, LocalDateTime.now());
            Rent forbiddenRent2 = new Rent(co1, c2, LocalDateTime.now());

            Assert.assertTrue(ClientRepo.add(c1));
            Assert.assertTrue(ClientRepo.add(c2));

            Assert.assertTrue(CourtRepo.add(co1));
            Assert.assertTrue(CourtRepo.add(co2));

            Assert.assertFalse(c1.isHasRent());
            Assert.assertFalse(co1.isRented());

            Assert.assertTrue(rentManager.add(rent));

            Assert.assertTrue(c1.isHasRent());
            Assert.assertTrue(co1.isRented());

            Assert.assertFalse(rentManager.add(forbiddenRent1));
            Assert.assertFalse(rentManager.add(forbiddenRent2));

            Rent rentToLeave = rentManager.find(1);

            Assert.assertTrue(rentManager.leave(rentToLeave));
            Assert.assertTrue(c1.isHasRent());
            Assert.assertTrue(co1.isRented());


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void rentRepoTest() {
        try (RentRepository rentRepo = new RentRepository();
             CourtRepository CourtRepo = new CourtRepository();
             ClientRepository ClientRepo = new ClientRepository()) {

            Rent rent1 = new Rent(co1, c1, LocalDateTime.now());
            Rent rent2 = new Rent(co2, c2, LocalDateTime.now());
            Rent rent3 = new Rent(co3, c3, LocalDateTime.now());

            Assert.assertTrue(CourtRepo.add(co1));
            Assert.assertTrue(CourtRepo.add(co2));
            Assert.assertTrue(CourtRepo.add(co3));

            Assert.assertTrue(ClientRepo.add(c1));
            Assert.assertTrue(ClientRepo.add(c2));
            Assert.assertTrue(ClientRepo.add(c3));


            Assert.assertTrue(rentRepo.add(rent1));
            Assert.assertTrue(rentRepo.add(rent2));
            Assert.assertTrue(rentRepo.add(rent3));

            Assert.assertEquals(rentRepo.find(1), rent1);
            Assert.assertEquals(rentRepo.findAll().size(), 3);
            Assert.assertTrue(rentRepo.remove(1));
            Assert.assertEquals(rentRepo.findAll().size(), 2);
        }
    }

    @Test
    public void courtRepoTest() {
        try (CourtRepository CourtRepo = new CourtRepository()) {

            Assert.assertTrue(CourtRepo.add(co1));
            Assert.assertTrue(CourtRepo.add(co2));
            Assert.assertTrue(CourtRepo.add(co3));

            Assert.assertEquals(CourtRepo.find(2),co2);
            Assert.assertEquals(CourtRepo.findAll().size(),3);
            Assert.assertTrue(CourtRepo.remove(2));
            Assert.assertEquals(CourtRepo.findAll().size(),2);
        }
    }

    @Test
    public void clientRepoTest() {
        try (ClientRepository ClientRepo = new ClientRepository()) {

            Assert.assertTrue(ClientRepo.add(c1));
            Assert.assertTrue(ClientRepo.add(c2));
            Assert.assertTrue(ClientRepo.add(c3));

            Assert.assertEquals(ClientRepo.find(2),c2);
            Assert.assertEquals(ClientRepo.findAll().size(),3);
            Assert.assertTrue(ClientRepo.remove(2));
            Assert.assertEquals(ClientRepo.findAll().size(),2);
        }
    }
}
