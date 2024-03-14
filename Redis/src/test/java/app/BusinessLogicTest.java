package app;

import org.example.entityMgd.ClientMgd;
import org.example.entityMgd.CourtMgd;
import org.example.entityMgd.FootballCourtMgd;
import org.example.entityMgd.RentMgd;
import org.example.repository.AbstractMongoRepository;
import org.example.repository.ClientMongoRepository;
import org.example.repository.CourtMongoRepository;
import org.example.repository.RentMongoRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class BusinessLogicTest {


    @Before
    public void setUp() {
        try(AbstractMongoRepository repo = new ClientMongoRepository()) {
            repo.getDatabase().drop();
        }
    }

    @Test
    public void testClientHasRent() {
        ClientMgd clientMgd = new ClientMgd(123, "Marek", "Blok","123234", 1);
        CourtMgd courtMgd = new FootballCourtMgd(123, 999, 999, 0, 999,43);

        try(ClientMongoRepository clientRepo  = new ClientMongoRepository();
            CourtMongoRepository courtRepo = new CourtMongoRepository();
            RentMongoRepository rentRepo = new RentMongoRepository()) {

            assertTrue(clientRepo.add(clientMgd));
            assertTrue(courtRepo.add(courtMgd));
            assertEquals(clientRepo.find(clientMgd.getId()).getHasRent(), 1);
            assertEquals(courtRepo.find(courtMgd.getId()).getIsRented(), 0);
            assertEquals(rentRepo.findAll().size(), 0);

            RentMgd rentMgd = new RentMgd(123, courtMgd, clientMgd, new Date(), null);
            assertFalse(rentRepo.add(rentMgd));
            assertNull(rentRepo.find(rentMgd.getId()));

            assertEquals(clientRepo.find(clientMgd.getId()).getHasRent(), 1);
            assertEquals(courtRepo.find(courtMgd.getId()).getIsRented(), 0);
            assertEquals(rentRepo.findAll().size(), 0);
        }
    }

    @Test
    public void testCourtIsRented() {
        ClientMgd clientMgd = new ClientMgd(456, "Marek", "Blok","123234", 0);
        CourtMgd courtMgd = new FootballCourtMgd(456, 999, 999, 1, 999,43);

        try(ClientMongoRepository clientRepo  = new ClientMongoRepository();
            CourtMongoRepository courtRepo = new CourtMongoRepository();
            RentMongoRepository rentRepo = new RentMongoRepository()) {

            assertTrue(clientRepo.add(clientMgd));
            assertTrue(courtRepo.add(courtMgd));
            assertEquals(clientRepo.find(clientMgd.getId()).getHasRent(), 0);
            assertEquals(courtRepo.find(courtMgd.getId()).getIsRented(), 1);
            assertEquals(rentRepo.findAll().size(), 0);

            RentMgd rentMgd = new RentMgd(456, courtMgd, clientMgd, new Date(), null);
            assertFalse(rentRepo.add(rentMgd));
            assertNull(rentRepo.find(rentMgd.getId()));

            assertEquals(clientRepo.find(clientMgd.getId()).getHasRent(), 0);
            assertEquals(courtRepo.find(courtMgd.getId()).getIsRented(), 1);
            assertEquals(rentRepo.findAll().size(), 0);
        }
    }

    @Test
    public void testCourtIsRentedAndClientHasRent() {
        ClientMgd clientMgd = new ClientMgd(678, "Marek", "Blok","123234", 1);
        CourtMgd courtMgd = new FootballCourtMgd(678, 999, 999, 1, 999,43);

        try(ClientMongoRepository clientRepo  = new ClientMongoRepository();
            CourtMongoRepository courtRepo = new CourtMongoRepository();
            RentMongoRepository rentRepo = new RentMongoRepository()) {

            assertTrue(clientRepo.add(clientMgd));
            assertTrue(courtRepo.add(courtMgd));
            assertEquals(clientRepo.find(clientMgd.getId()).getHasRent(), 1);
            assertEquals(courtRepo.find(courtMgd.getId()).getIsRented(), 1);
            assertEquals(rentRepo.findAll().size(), 0);

            RentMgd rentMgd = new RentMgd(678, courtMgd, clientMgd, new Date(), null);
            assertFalse(rentRepo.add(rentMgd));
            assertNull(rentRepo.find(rentMgd.getId()));

            assertEquals(clientRepo.find(clientMgd.getId()).getHasRent(), 1);
            assertEquals(courtRepo.find(courtMgd.getId()).getIsRented(), 1);
            assertEquals(rentRepo.findAll().size(), 0);
        }
    }
}

