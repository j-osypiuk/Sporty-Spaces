package benchmark;

import org.example.entity.Court;
import org.example.entity.FootballCourt;
import org.example.mapper.CourtMapper;
import org.example.repository.CourtMongoRepository;
import org.junit.Before;
import org.junit.Test;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Warmup(iterations = 0)
@Measurement(iterations = 1, timeUnit = TimeUnit.MICROSECONDS)
@Fork(1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
public class RedisRepositoryReadFromMongoBenchmarkTest {

    CourtMongoRepository mongoRepository = new CourtMongoRepository();
    FootballCourt footballCourt = new FootballCourt(1,1,1,1,1,1);

    @Before
    public void setUp() throws Exception {
        mongoRepository.getDatabase().getCollection("courts").drop();
        assertTrue(mongoRepository.add(CourtMapper.toMongoCourt(footballCourt)));
    }

    @Test
    @Benchmark
    public void dataReadFromMongoBenchmark() {
        Court court = CourtMapper.fromMongoCourt(mongoRepository.find(footballCourt.getId()));
    }
}
