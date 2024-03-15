import com.datastax.oss.driver.api.core.CqlIdentifier;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.querybuilder.QueryBuilder;
import com.datastax.oss.driver.api.querybuilder.relation.Relation;
import com.datastax.oss.driver.api.querybuilder.select.Select;
import org.example.entity.BasketballCourt;
import org.example.entity.FootballCourt;
import org.example.entity.VolleyballCourt;
import org.example.names.CourtIds;
import org.example.repository.CourtRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.literal;
import static org.example.mapper.CourtCustomMapper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class MapperTest {

    FootballCourt footballCourt = new FootballCourt(100,1.1,1.1,1,1,true, "");
    VolleyballCourt volleyballCourt = new VolleyballCourt(200,2.2,2.2,1,1,true, "");
    BasketballCourt basketballCourt = new BasketballCourt(300,3.3,3.3,1,1,true, "");

    @AfterEach
    void tearDown() {
        try(CourtRepository courtRepository = new CourtRepository()) {

            courtRepository.remove(footballCourt.getId());
            courtRepository.remove(volleyballCourt.getId());
            courtRepository.remove(basketballCourt.getId());
        }
    }

    @Test
    public void customMapperTest() {
        try(CourtRepository courtRepository = new CourtRepository()) {
            courtRepository.add(footballCourt);
            courtRepository.add(volleyballCourt);
            courtRepository.add(basketballCourt);

            Select selectFootballCourt = QueryBuilder.selectFrom(CqlIdentifier.fromCql("courts"))
                    .all()
                    .where(Relation.column(CourtIds.ID).isEqualTo(literal(footballCourt.getId())));

            Row footballRow = courtRepository.getSession().execute(selectFootballCourt.build()).one();
            FootballCourt mappedFootball = getFootballCourt(footballRow);

            assertEquals(footballCourt, mappedFootball);


            Select selectBasketballCourt = QueryBuilder.selectFrom(CqlIdentifier.fromCql("courts"))
                    .all()
                    .where(Relation.column(CourtIds.ID).isEqualTo(literal(basketballCourt.getId())));

            Row basketballRow = courtRepository.getSession().execute(selectBasketballCourt.build()).one();
            BasketballCourt mappedBasketball = getBasketballCourt(basketballRow);

            assertEquals(basketballCourt, mappedBasketball);


            Select selectVolleyballCourt = QueryBuilder.selectFrom(CqlIdentifier.fromCql("courts"))
                    .all()
                    .where(Relation.column(CourtIds.ID).isEqualTo(literal(volleyballCourt.getId())));

            Row volleyballRow = courtRepository.getSession().execute(selectVolleyballCourt.build()).one();
            VolleyballCourt mappedVolleyball = getVolleyballCourt(volleyballRow);

            assertEquals(volleyballCourt, mappedVolleyball);
        }
    }
}
