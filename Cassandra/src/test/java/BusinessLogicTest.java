import org.example.entity.Client;
import org.example.entity.Court;
import org.example.entity.FootballCourt;
import org.example.entity.Rent;
import org.example.manager.RentManager;
import org.example.repository.ClientRepository;
import org.example.repository.CourtRepository;
import org.example.repository.RentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


public class BusinessLogicTest {

    Client client = new Client(1,false,"Dave","Musial","123");
    FootballCourt footballCourt = new FootballCourt(100,1.1,1.1,1,1,false, "");
    Rent rent = new Rent(999,100,1, LocalDate.of(2022,10,10), null);

    @AfterEach
    void tearDown() {
        try(ClientRepository clientRepository = new ClientRepository();
            RentRepository rentRepository = new RentRepository();
            CourtRepository courtRepository = new CourtRepository()) {

            clientRepository.remove(client.getId());
            courtRepository.remove(footballCourt.getId());
            rentRepository.remove(rent.getId());
        }
    }

    @Test
    public void canAddRentTest() {
        try(ClientRepository clientRepository = new ClientRepository();
            RentManager rentManager = new RentManager();
            CourtRepository courtRepository = new CourtRepository()) {

            assertTrue(clientRepository.add(client));
            assertTrue(courtRepository.add(footballCourt));

            assertTrue(rentManager.addRent(rent));

            Client cClient = clientRepository.find(client.getId());
            Court cCourt = courtRepository.find(footballCourt.getId());
            assertTrue(cClient.isHasRent());
            assertTrue(cCourt.isRented());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void courtDoesntExistsAddRentTest() {
        try(ClientRepository clientRepository = new ClientRepository();
            RentManager rentManager = new RentManager();
            CourtRepository courtRepository = new CourtRepository()) {

            assertTrue(clientRepository.add(client));

            assertFalse(rentManager.addRent(rent));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void clientDoesntExistsAddRentTest() {
        try(ClientRepository clientRepository = new ClientRepository();
            RentManager rentManager = new RentManager();
            CourtRepository courtRepository = new CourtRepository()) {

            assertTrue(courtRepository.add(footballCourt));

            assertFalse(rentManager.addRent(rent));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void courtAndClientDontExistsAddRentTest() {
        try(ClientRepository clientRepository = new ClientRepository();
            RentManager rentManager = new RentManager();
            CourtRepository courtRepository = new CourtRepository()) {

            assertFalse(rentManager.addRent(rent));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void courtIsRentedAddRentTest() {
        try(ClientRepository clientRepository = new ClientRepository();
            RentManager rentManager = new RentManager();
            CourtRepository courtRepository = new CourtRepository()) {

            assertTrue(clientRepository.add(client));
            footballCourt.setRented(true);
            assertTrue(courtRepository.add(footballCourt));

            assertFalse(rentManager.addRent(rent));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void clientHasRentAddRentTest() {
        try(ClientRepository clientRepository = new ClientRepository();
            RentManager rentManager = new RentManager();
            CourtRepository courtRepository = new CourtRepository()) {

            assertTrue(courtRepository.add(footballCourt));
            client.setHasRent(true);
            assertTrue(clientRepository.add(client));

            assertFalse(rentManager.addRent(rent));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void courtIsRentedAndClientHasRentAddRentTest() {
        try(ClientRepository clientRepository = new ClientRepository();
            RentManager rentManager = new RentManager();
            CourtRepository courtRepository = new CourtRepository()) {

            footballCourt.setRented(true);
            assertTrue(courtRepository.add(footballCourt));
            client.setHasRent(true);
            assertTrue(clientRepository.add(client));

            assertFalse(rentManager.addRent(rent));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void canEndRent() {
        try(ClientRepository clientRepository = new ClientRepository();
            RentManager rentManager = new RentManager();
            RentRepository rentRepository = new RentRepository();
            CourtRepository courtRepository = new CourtRepository()) {

            footballCourt.setRented(true);
            assertTrue(courtRepository.add(footballCourt));
            client.setHasRent(true);
            assertTrue(clientRepository.add(client));
            assertTrue(rentRepository.add(rent));

            assertTrue(rentManager.endRent(rent));

            Client cClient = clientRepository.find(client.getId());
            Court cCourt = courtRepository.find(footballCourt.getId());
            Rent cRent = rentRepository.find(rent.getId());
            assertNotNull(cRent.getEndTime());
            assertFalse(cClient.isHasRent());
            assertFalse(cCourt.isRented());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void rentDoesntExistsEndRent() {
        try(ClientRepository clientRepository = new ClientRepository();
            RentManager rentManager = new RentManager();
            CourtRepository courtRepository = new CourtRepository()) {

            footballCourt.setRented(true);
            assertTrue(courtRepository.add(footballCourt));
            client.setHasRent(true);
            assertTrue(clientRepository.add(client));

            assertFalse(rentManager.endRent(rent));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
