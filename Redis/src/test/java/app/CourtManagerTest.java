package app;

import org.example.entity.BasketballCourt;
import org.example.entity.Court;
import org.example.entity.FootballCourt;
import org.example.entity.VolleyballCourt;
import org.example.manager.CourtManager;
import org.example.mapper.CourtMapper;
import org.example.repository.CourtMongoRepository;
import org.example.repository.RedisCourtRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CourtManagerTest {

    RedisCourtRepository redisRepository = new RedisCourtRepository();
    CourtMongoRepository mongoRepository = new CourtMongoRepository();
    CourtManager courtManager = new CourtManager();
    FootballCourt footballCourt = new FootballCourt(1,1,1,1,1,1);
    BasketballCourt basketCourt = new BasketballCourt(2,1,1,1,1,1);
    VolleyballCourt volleyballCourt = new VolleyballCourt(3,1,1,1,1,1);



    @Before
    public void setUp() throws Exception {
        mongoRepository.getDatabase().getCollection("courts").drop();
        redisRepository.deleteAllJson();
    }

    @Test
    public void addCourtTest() {
        assertEquals(courtManager.findAllCourts().size(), 0);
        assertTrue(courtManager.addCourt(footballCourt));
        assertTrue(courtManager.addCourt(basketCourt));
        assertTrue(courtManager.addCourt(volleyballCourt));
        assertEquals(courtManager.findAllCourts().size(), 3);


        assertNull(redisRepository.findJson(footballCourt.getId()));
        assertNull(redisRepository.findJson(basketCourt.getId()));
        assertNull(redisRepository.findJson(volleyballCourt.getId()));

        assertEquals(CourtMapper.fromMongoCourt(mongoRepository.find(footballCourt.getId())), footballCourt);
        assertEquals(CourtMapper.fromMongoCourt(mongoRepository.find(basketCourt.getId())), basketCourt);
        assertEquals(CourtMapper.fromMongoCourt(mongoRepository.find(volleyballCourt.getId())), volleyballCourt);
    }

    @Test
    public void removeCourtTest() {
        assertTrue(mongoRepository.add(CourtMapper.toMongoCourt(footballCourt)));
        assertTrue(mongoRepository.add(CourtMapper.toMongoCourt(basketCourt)));
        assertTrue(mongoRepository.add(CourtMapper.toMongoCourt(volleyballCourt)));

        assertTrue(redisRepository.addJson(footballCourt));
        assertTrue(redisRepository.addJson(basketCourt));
        assertTrue(redisRepository.addJson(volleyballCourt));

        assertTrue(courtManager.removeCourt(footballCourt.getId()));
        assertNull(CourtMapper.fromMongoCourt(mongoRepository.find(footballCourt.getId())));
        assertNull(redisRepository.findJson(footballCourt.getId()));

        assertTrue(courtManager.removeCourt(basketCourt.getId()));
        assertNull(CourtMapper.fromMongoCourt(mongoRepository.find(basketCourt.getId())));
        assertNull(redisRepository.findJson(basketCourt.getId()));

        assertTrue(courtManager.removeCourt(volleyballCourt.getId()));
        assertNull(CourtMapper.fromMongoCourt(mongoRepository.find(volleyballCourt.getId())));
        assertNull(redisRepository.findJson(volleyballCourt.getId()));
    }

    @Test
    public void findCourtFromRedisTest() {
        assertTrue(redisRepository.addJson(footballCourt));

        assertEquals(redisRepository.findJson(footballCourt.getId()), footballCourt);
    }

    @Test
    public void findCourtFromMongoTest() {
        assertTrue(mongoRepository.add(CourtMapper.toMongoCourt(footballCourt)));
        assertNull(redisRepository.findJson(footballCourt.getId()));

        assertEquals(courtManager.findCourt(footballCourt.getId()), footballCourt);

        assertEquals(CourtMapper.fromMongoCourt(mongoRepository.find(footballCourt.getId())), footballCourt);
        assertEquals(redisRepository.findJson(footballCourt.getId()), footballCourt);
    }


    @Test
    public void updateCourtTest() {
        assertTrue(mongoRepository.add(CourtMapper.toMongoCourt(footballCourt)));
        assertEquals(CourtMapper.fromMongoCourt(mongoRepository.find(footballCourt.getId())), footballCourt);
        assertNull(redisRepository.findJson(footballCourt.getId()));

        footballCourt.setGoalLength(99999);
        assertTrue(courtManager.updateCourt(footballCourt));
        assertEquals(CourtMapper.fromMongoCourt(mongoRepository.find(footballCourt.getId())), footballCourt);
        assertEquals(redisRepository.findJson(footballCourt.getId()), footballCourt);
    }

    @Test
    public void findAllCourtsTest() {
        assertEquals(courtManager.findAllCourts().size(), 0);
        assertTrue(courtManager.addCourt(footballCourt));
        assertTrue(courtManager.addCourt(basketCourt));
        assertTrue(courtManager.addCourt(volleyballCourt));

        List<Court> courts = courtManager.findAllCourts();
        assertEquals(courts.size(), 3);

        assertEquals(courts.get(0), footballCourt);
        assertEquals(courts.get(1), basketCourt);
        assertEquals(courts.get(2), volleyballCourt);
    }

    // Needs to turn off Redis container
    @Test
    public void lostConnectionToRedisTest() {
            assertTrue(mongoRepository.add(CourtMapper.toMongoCourt(footballCourt)));

            Court court = courtManager.findCourt(footballCourt.getId());
            assertEquals(court, footballCourt);
    }
}
