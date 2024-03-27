import org.example.entity.*;
import org.example.entityMgd.*;
import org.example.mapper.ClientMapper;
import org.example.mapper.CourtMapper;
import org.example.mapper.RentMapper;
import org.junit.Test;

import org.bson.Document;

import java.util.Date;

import static org.junit.Assert.*;

public class MapperTest {


    @Test
    public void fromMongoClientTest() {
        ClientMgd clientMgd = new ClientMgd(1, "Marek", "Kowalski", "3454564", 0);

        Client client = ClientMapper.fromMongoClient(clientMgd);
        assertEquals(client.getId(), clientMgd.getId());
        assertEquals(client.getFirstName(), clientMgd.getFirstName());
        assertEquals(client.getLastName(), clientMgd.getLastName());
        assertEquals(client.getPersonalID(), clientMgd.getPersonalID());
        assertEquals(client.getHasRent(), clientMgd.getHasRent());
    }

    @Test
    public void toMongoClientTest() {
        Client client = new Client(1, "Marek", "Kowalski", "3454564", 0);

        ClientMgd clientMgd = ClientMapper.toMongoClient(client);
        assertEquals(clientMgd.getId(), client.getId());
        assertEquals(clientMgd.getFirstName(), client.getFirstName());
        assertEquals(clientMgd.getLastName(), client.getLastName());
        assertEquals(clientMgd.getPersonalID(), client.getPersonalID());
        assertEquals(clientMgd.getHasRent(), client.getHasRent());
    }

    @Test
    public void toClientMgdTest() {
        Document clientDoc = new Document();
        clientDoc.append("_id", 1)
                .append("first_name", "Marek")
                .append("last_name", "Kowalski")
                .append("personal_ID", "123423443")
                .append("has_rent", 0);

        ClientMgd clientMgd = ClientMapper.toClientMgd(clientDoc);
        assertEquals(clientMgd.getId(), clientDoc.get("_id"));
        assertEquals(clientMgd.getFirstName(), clientDoc.get("first_name"));
        assertEquals(clientMgd.getLastName(), clientDoc.get("last_name"));
        assertEquals(clientMgd.getPersonalID(), clientDoc.get("personal_ID"));
        assertEquals(clientMgd.getHasRent(), clientDoc.get("has_rent"));
    }

    @Test
    public void toMongoCourtTest() {
        FootballCourt football = new FootballCourt(1, 1, 1, 0, 1, 1);
        BasketballCourt basketball = new BasketballCourt(2, 2, 2, 0, 2, 2);
        VolleyballCourt volleyball = new VolleyballCourt(3, 3, 3, 0, 3, 3);

        FootballCourtMgd footballCourtMgd = (FootballCourtMgd) CourtMapper.toMongoCourt(football);
        assertEquals(footballCourtMgd.getId(), football.getId());
        assertEquals(footballCourtMgd.getWidth(), football.getWidth(), 0.001);
        assertEquals(footballCourtMgd.getLength(), football.getLength(),0.001);
        assertEquals(footballCourtMgd.getIsRented(), football.getIsRented());
        assertEquals(footballCourtMgd.getGoalWidth(), football.getGoalWidth(), 0.001);
        assertEquals(footballCourtMgd.getGoalLength(), football.getGoalLength(), 0.001);

        BasketballCourtMgd basketballCourtMgd = (BasketballCourtMgd) CourtMapper.toMongoCourt(basketball);
        assertEquals(basketballCourtMgd.getId(), basketball.getId());
        assertEquals(basketballCourtMgd.getWidth(), basketball.getWidth(), 0.001);
        assertEquals(basketballCourtMgd.getLength(), basketball.getLength(),0.001);
        assertEquals(basketballCourtMgd.getIsRented(), basketball.getIsRented());
        assertEquals(basketballCourtMgd.getBasketHeight(), basketball.getBasketHeight(), 0.001);
        assertEquals(basketballCourtMgd.getBasketRadius(), basketball.getBasketRadius(), 0.001);

        VolleyballCourtMgd volleyballCourtMgd = (VolleyballCourtMgd) CourtMapper.toMongoCourt(volleyball);
        assertEquals(volleyballCourtMgd.getId(), volleyball.getId());
        assertEquals(volleyballCourtMgd.getWidth(), volleyball.getWidth(), 0.001);
        assertEquals(volleyballCourtMgd.getLength(), volleyball.getLength(),0.001);
        assertEquals(volleyballCourtMgd.getIsRented(), volleyball.getIsRented());
        assertEquals(volleyballCourtMgd.getNetLength(), volleyball.getNetLength(), 0.001);
        assertEquals(volleyballCourtMgd.getNetWidth(), volleyball.getNetWidth(), 0.001);
    }

    @Test
    public void fromMongoCourtTest() {
        FootballCourtMgd footballMgd = new FootballCourtMgd(1, 1, 1, 0, 1, 1);
        BasketballCourtMgd basketballMgd = new BasketballCourtMgd(2, 2, 2, 0, 2, 2);
        VolleyballCourtMgd volleyballMgd = new VolleyballCourtMgd(3, 3, 3, 0, 3, 3);

        FootballCourt footballCourt = (FootballCourt) CourtMapper.fromMongoCourt(footballMgd);
        assertEquals(footballCourt.getId(), footballMgd.getId());
        assertEquals(footballCourt.getWidth(), footballMgd.getWidth(), 0.001);
        assertEquals(footballCourt.getLength(), footballMgd.getLength(),0.001);
        assertEquals(footballCourt.getIsRented(), footballMgd.getIsRented());
        assertEquals(footballCourt.getGoalWidth(), footballMgd.getGoalWidth(), 0.001);
        assertEquals(footballCourt.getGoalLength(), footballMgd.getGoalLength(), 0.001);

        BasketballCourt basketballCourt = (BasketballCourt) CourtMapper.fromMongoCourt(basketballMgd);
        assertEquals(basketballCourt.getId(), basketballMgd.getId());
        assertEquals(basketballCourt.getWidth(), basketballMgd.getWidth(), 0.001);
        assertEquals(basketballCourt.getLength(), basketballMgd.getLength(),0.001);
        assertEquals(basketballCourt.getIsRented(), basketballMgd.getIsRented());
        assertEquals(basketballCourt.getBasketHeight(), basketballMgd.getBasketHeight(), 0.001);
        assertEquals(basketballCourt.getBasketRadius(), basketballMgd.getBasketRadius(), 0.001);

        VolleyballCourt volleyballCourt = (VolleyballCourt) CourtMapper.fromMongoCourt(volleyballMgd);
        assertEquals(volleyballCourt.getId(), volleyballMgd.getId());
        assertEquals(volleyballCourt.getWidth(), volleyballMgd.getWidth(), 0.001);
        assertEquals(volleyballCourt.getLength(), volleyballMgd.getLength(),0.001);
        assertEquals(volleyballCourt.getIsRented(), volleyballMgd.getIsRented());
        assertEquals(volleyballCourt.getNetLength(), volleyballMgd.getNetLength(), 0.001);
        assertEquals(volleyballCourt.getNetWidth(), volleyballMgd.getNetWidth(), 0.001);
    }

    @Test
    public void toCourtMgdTest() {
        Document footballDoc = new Document();
        footballDoc.append("_id", 1)
                .append("_clazz", "football")
                .append("width", 1.34)
                .append("length", 1.41)
                .append("is_rented", 0)
                .append("goal_width", 1.23)
                .append("goal_length", 1.23);

        Document basketballDoc = new Document();
        basketballDoc.append("_id", 1)
                .append("_clazz", "basketball")
                .append("width", 1.45)
                .append("length", 1.65)
                .append("is_rented", 0)
                .append("basket_height", 1.32)
                .append("basket_radius", 1.43);

        Document volleyballDoc = new Document();
        volleyballDoc.append("_id", 1)
                .append("_clazz", "volleyball")
                .append("width", 1.43)
                .append("length", 1.43)
                .append("is_rented", 0)
                .append("net_length", 1.54)
                .append("net_width", 1.64);

        FootballCourtMgd footballCourtMgd = (FootballCourtMgd) CourtMapper.toCourtMgd(footballDoc);
        assertEquals(footballCourtMgd.getId(), footballDoc.get("_id"));
        assertEquals(footballCourtMgd.getWidth(), (double) footballDoc.get("width"), 0.001);
        assertEquals(footballCourtMgd.getLength(), (double) footballDoc.get("length"),0.001);
        assertEquals(footballCourtMgd.getIsRented(), footballDoc.get("is_rented"));
        assertEquals(footballCourtMgd.getGoalWidth(), (double) footballDoc.get("goal_width"), 0.001);
        assertEquals(footballCourtMgd.getGoalLength(), (double) footballDoc.get("goal_length"), 0.001);

        BasketballCourtMgd basketballCourtMgd = (BasketballCourtMgd) CourtMapper.toCourtMgd(basketballDoc);
        assertEquals(basketballCourtMgd.getId(), basketballDoc.get("_id"));
        assertEquals(basketballCourtMgd.getWidth(), (double) basketballDoc.get("width"), 0.001);
        assertEquals(basketballCourtMgd.getLength(), (double) basketballDoc.get("length"),0.001);
        assertEquals(basketballCourtMgd.getIsRented(), basketballDoc.get("is_rented"));
        assertEquals(basketballCourtMgd.getBasketHeight(), (double) basketballDoc.get("basket_height"), 0.001);
        assertEquals(basketballCourtMgd.getBasketRadius(), (double) basketballDoc.get("basket_radius"), 0.001);

        VolleyballCourtMgd volleyballCourtMgd = (VolleyballCourtMgd) CourtMapper.toCourtMgd(volleyballDoc);
        assertEquals(volleyballCourtMgd.getId(), volleyballDoc.get("_id"));
        assertEquals(volleyballCourtMgd.getWidth(), (double) volleyballDoc.get("width"), 0.001);
        assertEquals(volleyballCourtMgd.getLength(), (double) volleyballDoc.get("length"),0.001);
        assertEquals(volleyballCourtMgd.getIsRented(), volleyballDoc.get("is_rented"));
        assertEquals(volleyballCourtMgd.getNetLength(), (double) volleyballDoc.get("net_length"), 0.001);
        assertEquals(volleyballCourtMgd.getNetWidth(), (double) volleyballDoc.get("net_width"), 0.001);
    }

    @Test
    public void fromMongoRentTest() {
        ClientMgd clientMgd = new ClientMgd(1, "Marek", "Blok", "3424", 0);
        FootballCourtMgd footballMgd = new FootballCourtMgd(1, 1, 1, 0, 1, 1);
        RentMgd rentMgd = new RentMgd(1, footballMgd, clientMgd, new Date(), new Date());

        Rent rent = RentMapper.fromMongoRent(rentMgd);
        FootballCourt court = (FootballCourt) rent.getCourt();
        assertEquals(rent.getId(), rentMgd.getId());
        assertEquals(rent.getStartTime(), rentMgd.getStartTime());
        assertEquals(rent.getEndTime(), rentMgd.getEndTime());
        assertEquals(rent.getClient().getId(), rentMgd.getClient().getId());
        assertEquals(rent.getClient().getFirstName(), rentMgd.getClient().getFirstName());
        assertEquals(rent.getClient().getLastName(), rentMgd.getClient().getLastName());
        assertEquals(rent.getClient().getPersonalID(), rentMgd.getClient().getPersonalID());
        assertEquals(rent.getClient().getHasRent(), rentMgd.getClient().getHasRent());
        assertEquals(rent.getCourt().getId(), rentMgd.getCourt().getId(), 0.001);
        assertEquals(rent.getCourt().getWidth(), rentMgd.getCourt().getWidth(), 0.001);
        assertEquals(rent.getCourt().getLength(), rentMgd.getCourt().getLength(), 0.001);
        assertEquals(rent.getCourt().getIsRented(), rentMgd.getCourt().getIsRented());
        assertEquals(court.getGoalWidth(), footballMgd.getGoalWidth(), 0.001);
        assertEquals(court.getGoalLength(), footballMgd.getGoalLength(), 0.001);
    }

    @Test
    public void toMongoRentTest() {
        Client client = new Client(1, "Marek", "Blok", "3424", 0);
        FootballCourt football = new FootballCourt(1, 1, 1, 0, 1, 1);
        Rent rent = new Rent(1, football, client, new Date(), new Date());

        RentMgd rentMgd = RentMapper.toMongoRent(rent);
        FootballCourtMgd courtMgd = (FootballCourtMgd) rentMgd.getCourt();
        assertEquals(rentMgd.getId(), rent.getId());
        assertEquals(rentMgd.getStartTime(), rent.getStartTime());
        assertEquals(rentMgd.getEndTime(), rent.getEndTime());
        assertEquals(rentMgd.getClient().getId(), rent.getClient().getId());
        assertEquals(rentMgd.getClient().getFirstName(), rent.getClient().getFirstName());
        assertEquals(rentMgd.getClient().getLastName(), rent.getClient().getLastName());
        assertEquals(rentMgd.getClient().getPersonalID(), rent.getClient().getPersonalID());
        assertEquals(rentMgd.getClient().getHasRent(), rent.getClient().getHasRent());
        assertEquals(rentMgd.getCourt().getId(), rent.getCourt().getId(), 0.001);
        assertEquals(rentMgd.getCourt().getWidth(), rent.getCourt().getWidth(), 0.001);
        assertEquals(rentMgd.getCourt().getLength(), rent.getCourt().getLength(), 0.001);
        assertEquals(rentMgd.getCourt().getIsRented(), rent.getCourt().getIsRented());
        assertEquals(courtMgd.getGoalWidth(), football.getGoalWidth(), 0.001);
        assertEquals(courtMgd.getGoalLength(), football.getGoalLength(), 0.001);
    }

    @Test
    public void toRentMgdTest() {
        Document clientDoc = new Document();
        clientDoc.append("_id", 1)
                .append("first_name", "Marek")
                .append("last_name", "Kowalski")
                .append("personal_ID", "123423443")
                .append("has_rent", 0);

        Document footballDoc = new Document();
        footballDoc.append("_id", 1)
                .append("_clazz", "football")
                .append("width", 1.34)
                .append("length", 1.41)
                .append("is_rented", 0)
                .append("goal_width", 1.23)
                .append("goal_length", 1.23);

        Document rentDoc = new Document();
        rentDoc.append("_id", 1)
                .append("court", footballDoc)
                .append("client", clientDoc)
                .append("start_time", new Date())
                .append("end_time", new Date());

        RentMgd rentMgd = RentMapper.toRentMgd(rentDoc);
        FootballCourtMgd footballMgd = (FootballCourtMgd) rentMgd.getCourt();
        assertEquals(rentMgd.getId(), rentDoc.get("_id"));
        assertEquals(rentMgd.getCourt().getId(), rentDoc.get("court", Document.class).get("_id"));
        assertEquals(rentMgd.getCourt().getLength(), (double) rentDoc.get("court", Document.class).get("length"), 0.001);
        assertEquals(rentMgd.getCourt().getWidth(), (double) rentDoc.get("court", Document.class).get("width"), 0.001);
        assertEquals(rentMgd.getCourt().getIsRented(), rentDoc.get("court", Document.class).get("is_rented"));
        assertEquals(footballMgd.getGoalWidth(), (double) rentDoc.get("court", Document.class).get("goal_width"), 0.001);
        assertEquals(footballMgd.getGoalLength(), (double) rentDoc.get("court", Document.class).get("goal_length"), 0.001);
        assertEquals(rentMgd.getClient().getId(), rentDoc.get("client", Document.class).get("_id"));
        assertEquals(rentMgd.getClient().getFirstName(), rentDoc.get("client", Document.class).get("first_name"));
        assertEquals(rentMgd.getClient().getLastName(), rentDoc.get("client", Document.class).get("last_name"));
        assertEquals(rentMgd.getClient().getPersonalID(), rentDoc.get("client", Document.class).get("personal_ID"));
        assertEquals(rentMgd.getClient().getHasRent(), rentDoc.get("client", Document.class).get("has_rent"));
    }
}

