package org.example.queryProvider;

import com.datastax.oss.driver.api.core.CqlIdentifier;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.mapper.MapperContext;
import com.datastax.oss.driver.api.mapper.entity.EntityHelper;
import com.datastax.oss.driver.api.querybuilder.QueryBuilder;
import com.datastax.oss.driver.api.querybuilder.relation.Relation;
import com.datastax.oss.driver.api.querybuilder.select.Select;
import org.example.entity.BasketballCourt;
import org.example.entity.Court;
import org.example.entity.FootballCourt;
import org.example.entity.VolleyballCourt;
import org.example.names.CourtIds;

import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.literal;
import static org.example.mapper.CourtCustomMapper.*;

public class CourtQueryProvider {

    private final CqlSession session;
    private EntityHelper<FootballCourt> footballCourtEntityHelper;
    private EntityHelper<BasketballCourt> basketballCourtEntityHelper;
    private EntityHelper<VolleyballCourt> volleyballCourtEntityHelper;
    public CourtQueryProvider(MapperContext ctx, EntityHelper<FootballCourt> footballCourtEntityHelper, EntityHelper<BasketballCourt> basketballCourtEntityHelper, EntityHelper<VolleyballCourt> volleyballCourtEntityHelper) {
        this.session = ctx.getSession();
        this.footballCourtEntityHelper = footballCourtEntityHelper;
        this.basketballCourtEntityHelper = basketballCourtEntityHelper;
        this.volleyballCourtEntityHelper = volleyballCourtEntityHelper;
    }

    public void save(Court court) {
        session.execute(
                switch (court.getDiscriminator()) {
                    case CourtIds.FOOTBALL -> {
                        FootballCourt footballCourt = (FootballCourt) court;
                        yield session.prepare(footballCourtEntityHelper.insert().build())
                                .bind()
                                .setInt(CourtIds.ID, footballCourt.getId())
                                .setDouble(CourtIds.WIDTH, footballCourt.getWidth())
                                .setDouble(CourtIds.LENGTH, footballCourt.getLength())
                                .setBoolean(CourtIds.IS_RENTED, footballCourt.isRented())
                                .setString(CourtIds.DISCRIMINATOR, footballCourt.getDiscriminator())
                                .setDouble(CourtIds.GOAL_WIDTH, footballCourt.getGoalWidth())
                                .setDouble(CourtIds.GOAL_LENGTH, footballCourt.getGoalLength());
                    }
                    case CourtIds.BASKETBALL -> {
                        BasketballCourt basketballCourt = (BasketballCourt) court;
                        yield session.prepare(basketballCourtEntityHelper.insert().build())
                                .bind()
                                .setInt(CourtIds.ID, basketballCourt.getId())
                                .setDouble(CourtIds.WIDTH, basketballCourt.getWidth())
                                .setDouble(CourtIds.LENGTH, basketballCourt.getLength())
                                .setBoolean(CourtIds.IS_RENTED, basketballCourt.isRented())
                                .setString(CourtIds.DISCRIMINATOR, basketballCourt.getDiscriminator())
                                .setDouble(CourtIds.BASKET_HEIGHT, basketballCourt.getBasketHeight())
                                .setDouble(CourtIds.BASKET_RADIUS, basketballCourt.getBasketRadius());
                    }
                    case CourtIds.VOLLEYBALL -> {
                        VolleyballCourt volleyballCourt = (VolleyballCourt) court;
                        yield session.prepare(volleyballCourtEntityHelper.insert().build())
                                .bind()
                                .setInt(CourtIds.ID, volleyballCourt.getId())
                                .setDouble(CourtIds.WIDTH, volleyballCourt.getWidth())
                                .setDouble(CourtIds.LENGTH, volleyballCourt.getLength())
                                .setBoolean(CourtIds.IS_RENTED, volleyballCourt.isRented())
                                .setString(CourtIds.DISCRIMINATOR, volleyballCourt.getDiscriminator())
                                .setDouble(CourtIds.NET_WIDTH, volleyballCourt.getNetWidth())
                                .setDouble(CourtIds.NET_LENGTH, volleyballCourt.getNetLength());
                    }
                    default -> throw new IllegalArgumentException();
                }
        );
    }

    public Court findById(int id) {
        Select selectCourt = QueryBuilder.selectFrom(CqlIdentifier.fromCql("courts"))
                                    .all()
                                    .where(Relation.column(CourtIds.ID).isEqualTo(literal(id)));

        Row row = session.execute(selectCourt.build()).one();
        String discriminator = row.getString(CourtIds.DISCRIMINATOR);

        return switch (discriminator) {
            case CourtIds.FOOTBALL -> getFootballCourt(row);
            case CourtIds.BASKETBALL -> getBasketballCourt(row);
            case CourtIds.VOLLEYBALL -> getVolleyballCourt(row);
            default -> throw new IllegalArgumentException();
        };
    }
}
