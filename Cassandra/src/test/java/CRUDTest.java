import org.example.entity.*;
import org.example.repository.ClientRepository;
import org.example.repository.CourtRepository;
import org.example.repository.RentRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.time.LocalDate;


import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CRUDTest {

    Client c1 = new Client(1,true,"Dave","Musial","123");
    Client c2 = new Client(2,true,"Jenny","Perry","456");
    Client c3 = new Client(3,true,"Alan","Bloom","123567");

    FootballCourt footballCourt = new FootballCourt(100,1.1,1.1,1,1,true, "");
    VolleyballCourt volleyballCourt = new VolleyballCourt(200,2.2,2.2,1,1,true, "");
    BasketballCourt basketballCourt = new BasketballCourt(300,3.3,3.3,1,1,true, "");

    Rent r1 = new Rent(999,100,3, LocalDate.of(2022,10,10), null);
    Rent r2 = new Rent(899,200,2, LocalDate.of(2021,11,11), null);
    Rent r3 = new Rent(799,300,1, LocalDate.of(2020,12,12), null);


    @Test
    @Order(1)
    public void createTest() {
        try(ClientRepository clientRepository = new ClientRepository();
            RentRepository rentRepository = new RentRepository();
            CourtRepository courtRepository = new CourtRepository()) {

            assertTrue(clientRepository.add(c1));
            assertTrue(clientRepository.add(c2));
            assertTrue(clientRepository.add(c3));

            assertTrue(courtRepository.add(footballCourt));
            assertTrue(courtRepository.add(basketballCourt));
            assertTrue(courtRepository.add(volleyballCourt));

            assertTrue(rentRepository.add(r1));
            assertTrue(rentRepository.add(r2));
            assertTrue(rentRepository.add(r3));
        }
    }

    @Test
    @Order(2)
    public void readTest() {
        try(ClientRepository clientRepository = new ClientRepository();
            RentRepository rentRepository = new RentRepository();
            CourtRepository courtRepository = new CourtRepository()) {

            assertEquals(clientRepository.find(c1.getId()),c1);
            assertEquals(clientRepository.find(c2.getId()),c2);
            assertEquals(clientRepository.find(c3.getId()),c3);

            assertEquals(courtRepository.find(footballCourt.getId()),footballCourt);
            assertEquals(courtRepository.find(basketballCourt.getId()),basketballCourt);
            assertEquals(courtRepository.find(volleyballCourt.getId()),volleyballCourt);

            assertEquals(rentRepository.find(r1.getId()),r1);
            assertEquals(rentRepository.find(r2.getId()),r2);
            assertEquals(rentRepository.find(r3.getId()),r3);
        }
    }

    @Test
    @Order(3)
    public void updateTest() {
        try(ClientRepository clientRepository = new ClientRepository();
            RentRepository rentRepository = new RentRepository();
            CourtRepository courtRepository = new CourtRepository()) {

            //Dave --> Matthew
            c1.setFirstName("Matthew");
            assertTrue(clientRepository.update(c1));
            Client cc = clientRepository.find(1);
            assertEquals(c1.getFirstName(), cc.getFirstName());

            //1.1 --> 1.67
            footballCourt.setGoalLength(1.67);
            assertTrue(courtRepository.update(footballCourt));
            FootballCourt cf = (FootballCourt) courtRepository.find(100);
            assertEquals(footballCourt.getGoalLength(), cf.getGoalLength(), 0.001);

            //end rent and set EndTime to value different from null
            assertTrue(rentRepository.update(r1));
            Rent cr = rentRepository.find(r1.getId());
            assertNotNull(cr.getEndTime());
        }
    }


    @Test
    @Order(4)
    public void deleteTest() {
        try(ClientRepository clientRepository = new ClientRepository();
            RentRepository rentRepository = new RentRepository();
            CourtRepository courtRepository = new CourtRepository()) {

            assertTrue(clientRepository.remove(c1.getId()));
            assertTrue(clientRepository.remove(c2.getId()));
            assertTrue(clientRepository.remove(c3.getId()));
            assertNull(clientRepository.find(c1.getId()));
            assertNull(clientRepository.find(c2.getId()));
            assertNull(clientRepository.find(c3.getId()));

            assertTrue(courtRepository.remove(footballCourt.getId()));
            assertTrue(courtRepository.remove(basketballCourt.getId()));
            assertTrue(courtRepository.remove(volleyballCourt.getId()));
            assertNull(courtRepository.find(footballCourt.getId()));
            assertNull(courtRepository.find(basketballCourt.getId()));
            assertNull(courtRepository.find(volleyballCourt.getId()));

            assertTrue(rentRepository.remove(r1.getId()));
            assertTrue(rentRepository.remove(r2.getId()));
            assertTrue(rentRepository.remove(r3.getId()));
            assertNull(rentRepository.find(r1.getId()));
            assertNull(rentRepository.find(r2.getId()));
            assertNull(rentRepository.find(r3.getId()));
        }
    }

}
