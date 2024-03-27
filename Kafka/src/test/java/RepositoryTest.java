import org.example.entityMgd.*;
import org.example.repository.ClientRepository;
import org.example.repository.CourtRepository;
import org.example.repository.RentRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class RepositoryTest {

    private ClientRepository clientRepo = new ClientRepository();
    ;
    private CourtRepository courtRepo = new CourtRepository();
    private RentRepository rentRepo = new RentRepository();
    private ClientMgd clientMgd;
    private FootballCourtMgd footballMgd;
    private BasketballCourtMgd basketballMgd;
    private VolleyballCourtMgd volleyballMgd;
    private RentMgd rentMgd;

    @Before
    public void setUp() throws Exception {
        clientRepo.getDatabase().getCollection("rents").drop();
        clientRepo.getDatabase().getCollection("courts").drop();
        clientRepo.getDatabase().getCollection("clients").drop();
        clientMgd = new ClientMgd(1, "Marek", "Blok", "3424", 0);
        footballMgd = new FootballCourtMgd(1, 1, 1, 0, 1, 1);
        basketballMgd = new BasketballCourtMgd(2, 2, 2, 0, 2, 2);
        volleyballMgd = new VolleyballCourtMgd(3, 3, 3, 0, 3, 3);
        rentMgd = new RentMgd(1, footballMgd, clientMgd, new Date(), null);
    }

    @After
    public void tearDown() throws Exception {
        clientRepo.close();
        courtRepo.close();
        rentRepo.close();
    }


    @Test
    public void testClientRepo() {

        // create
        assertEquals(clientRepo.findAll().size(), 0);
        assertTrue(clientRepo.add(clientMgd));
        assertEquals(clientRepo.findAll().size(), 1);

        // read
        ClientMgd mongoClient = clientRepo.find(clientMgd.getId());
        assertEquals(clientMgd.getId(), mongoClient.getId());

        // update
        String newFirstName = "Bartek";
        clientMgd.setFirstName(newFirstName);
        assertEquals(clientMgd.getFirstName(), newFirstName);
        assertTrue(clientRepo.update(clientMgd));
        ClientMgd updatedMongoClient = clientRepo.find(clientMgd.getId());
        assertEquals(updatedMongoClient.getFirstName(), newFirstName);

        // delete
        assertTrue(clientRepo.remove(clientMgd.getId()));
        assertEquals(clientRepo.findAll().size(), 0);
    }

    @Test
    public void testCourtRepo() {

        // create
        assertEquals(courtRepo.findAll().size(), 0);
        assertTrue(courtRepo.add(footballMgd));
        assertTrue(courtRepo.add(basketballMgd));
        assertTrue(courtRepo.add(volleyballMgd));
        assertEquals(courtRepo.findAll().size(), 3);

        // read
        CourtMgd mongoFootball = courtRepo.find(footballMgd.getId());
        assertEquals(footballMgd.getId(), mongoFootball.getId());
        CourtMgd mongoBasketball = courtRepo.find(basketballMgd.getId());
        assertEquals(basketballMgd.getId(), mongoBasketball.getId());
        CourtMgd mongoVolleyball = courtRepo.find(volleyballMgd.getId());
        assertEquals(volleyballMgd.getId(), mongoVolleyball.getId());

        // update
        int newWidth = 999;
        footballMgd.setWidth(newWidth);
        basketballMgd.setWidth(newWidth);
        volleyballMgd.setWidth(newWidth);
        assertEquals(footballMgd.getWidth(), newWidth);
        assertEquals(basketballMgd.getWidth(), newWidth);
        assertEquals(volleyballMgd.getWidth(), newWidth);
        assertTrue(courtRepo.update(footballMgd));
        assertTrue(courtRepo.update(basketballMgd));
        assertTrue(courtRepo.update(volleyballMgd));
        CourtMgd updatedFootball = courtRepo.find(footballMgd.getId());
        CourtMgd updatedBasketball = courtRepo.find(basketballMgd.getId());
        CourtMgd updatedVolleyball = courtRepo.find(volleyballMgd.getId());
        assertEquals(updatedFootball.getWidth(), newWidth);
        assertEquals(updatedBasketball.getWidth(), newWidth);
        assertEquals(updatedVolleyball.getWidth(), newWidth);

        // delete
        assertTrue(courtRepo.remove(footballMgd.getId()));
        assertTrue(courtRepo.remove(basketballMgd.getId()));
        assertTrue(courtRepo.remove(volleyballMgd.getId()));
        assertEquals(courtRepo.findAll().size(), 0);
    }

    @Test
    public void testRentRepo() {

        // create
        assertEquals(rentRepo.findAll().size(), 0);
        assertTrue(clientRepo.add(rentMgd.getClient()));
        assertTrue(courtRepo.add(rentMgd.getCourt()));
        assertEquals(clientRepo.find(rentMgd.getClient().getId()).getHasRent(), 0);
        assertEquals(courtRepo.find(rentMgd.getCourt().getId()).getIsRented(), 0);
        assertTrue(rentRepo.add(rentMgd));
        assertEquals(rentRepo.findAll().size(), 1);
        assertNull(rentRepo.find(rentMgd.getId()).getEndTime());
        assertEquals(clientRepo.find(rentMgd.getClient().getId()).getHasRent(), 1);
        assertEquals(courtRepo.find(rentMgd.getCourt().getId()).getIsRented(), 1);

        // read
        RentMgd mongoRent = rentRepo.find(rentMgd.getId());
        assertEquals(rentMgd.getId(), mongoRent.getId());

        // update
        Date formerDate = rentMgd.getStartTime();
        Date newDate = new Date();
        rentMgd.setStartTime(newDate);
        assertNotEquals(rentMgd.getStartTime(), formerDate);
        assertEquals(rentMgd.getStartTime(), newDate);
        assertTrue(rentRepo.update(rentMgd));
        RentMgd updatedRent = rentRepo.find(rentMgd.getId());
        assertEquals(updatedRent.getStartTime(), newDate);

        // end rent
        assertTrue(rentRepo.endRent(rentMgd));
        assertNotNull(rentRepo.find(rentMgd.getId()).getEndTime());
        assertEquals(clientRepo.find(rentMgd.getClient().getId()).getHasRent(), 0);
        assertEquals(courtRepo.find(rentMgd.getCourt().getId()).getIsRented(), 0);

        // delete
        assertTrue(rentRepo.remove(rentMgd.getId()));
        assertEquals(rentRepo.findAll().size(), 0);
    }
}