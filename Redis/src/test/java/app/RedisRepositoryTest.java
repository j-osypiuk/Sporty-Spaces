package app;

import org.example.entity.BasketballCourt;
import org.example.entity.Court;
import org.example.entity.FootballCourt;
import org.example.entity.VolleyballCourt;
import org.example.repository.RedisCourtRepository;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RedisRepositoryTest {
    RedisCourtRepository repository = new RedisCourtRepository();
    FootballCourt footballCourt = new FootballCourt(1,1,1,1,1,1);
    BasketballCourt basketCourt = new BasketballCourt(2,1,1,1,1,1);
    VolleyballCourt volleyballCourt = new VolleyballCourt(3,1,1,1,1,1);



    @Before
    public void setUp() throws Exception {
        repository.deleteAllJson();
    }

    @Test
    public void addTest() {
        assertTrue(repository.addJson(footballCourt));
        assertTrue(repository.addJson(basketCourt));
        assertTrue(repository.addJson(volleyballCourt));

        Court fcourt = repository.findJson(footballCourt.getId());
        assertEquals(fcourt,footballCourt);

        Court bcourt = repository.findJson(basketCourt.getId());
        assertEquals(bcourt, basketCourt);

        Court vcourt = repository.findJson(volleyballCourt.getId());
        assertEquals(vcourt,volleyballCourt);

    }

    @Test
    public void findTest() {
        assertTrue(repository.addJson(footballCourt));
        Court fcourt = repository.findJson(footballCourt.getId());
        assertEquals(fcourt,footballCourt);

        Court nullCourt = repository.findJson(999);
        assertNull(nullCourt);
    }

//    // Needs to turn off redis container
//    @Test
//    public void lostConnectionToRedisTest() {
//        try(CourtMongoRepository courtRepository = new CourtMongoRepository()) {
//            courtRepository.add(CourtMapper.toMongoCourt(footballTestCourt));
//
//            Court court = repository.findJson(11);
//            assertEquals(court, footballTestCourt);
//        }
//    }

    @Test
    public void deleteTest() {
        assertTrue(repository.addJson(footballCourt));

        assertTrue(repository.deleteJson(footballCourt.getId()));

        assertNull(repository.findJson(footballCourt.getId()));
    }

    @Test
    public void flushTest() {
        assertTrue(repository.addJson(footballCourt));
        assertTrue(repository.addJson(basketCourt));
        assertTrue(repository.addJson(volleyballCourt));

        assertTrue(repository.deleteAllJson());

        assertNull(repository.findJson(footballCourt.getId()));
        assertNull(repository.findJson(basketCourt.getId()));
        assertNull(repository.findJson(volleyballCourt.getId()));
    }
}
