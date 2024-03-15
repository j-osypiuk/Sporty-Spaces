package org.example.mapper;

import com.datastax.oss.driver.api.core.cql.Row;
import org.example.entity.BasketballCourt;
import org.example.entity.FootballCourt;
import org.example.entity.VolleyballCourt;
import org.example.names.CourtIds;

public class CourtCustomMapper {

    public static FootballCourt getFootballCourt(Row footballCourt) {
        return new FootballCourt(
                footballCourt.getInt(CourtIds.ID),
                footballCourt.getDouble(CourtIds.GOAL_WIDTH),
                footballCourt.getDouble(CourtIds.GOAL_LENGTH),
                footballCourt.getDouble(CourtIds.WIDTH),
                footballCourt.getDouble(CourtIds.LENGTH),
                footballCourt.getBoolean(CourtIds.IS_RENTED),
                footballCourt.getString(CourtIds.DISCRIMINATOR)
        );
    }

    public static BasketballCourt getBasketballCourt(Row basketballCourt) {
        return new BasketballCourt(
                basketballCourt.getInt(CourtIds.ID),
                basketballCourt.getDouble(CourtIds.BASKET_HEIGHT),
                basketballCourt.getDouble(CourtIds.BASKET_RADIUS),
                basketballCourt.getDouble(CourtIds.WIDTH),
                basketballCourt.getDouble(CourtIds.LENGTH),
                basketballCourt.getBoolean(CourtIds.IS_RENTED),
                basketballCourt.getString(CourtIds.DISCRIMINATOR)
        );
    }

    public static VolleyballCourt getVolleyballCourt(Row volleyballCourt) {
        return new VolleyballCourt(
                volleyballCourt.getInt(CourtIds.ID),
                volleyballCourt.getDouble(CourtIds.NET_LENGTH),
                volleyballCourt.getDouble(CourtIds.NET_WIDTH),
                volleyballCourt.getDouble(CourtIds.WIDTH),
                volleyballCourt.getDouble(CourtIds.LENGTH),
                volleyballCourt.getBoolean(CourtIds.IS_RENTED),
                volleyballCourt.getString(CourtIds.DISCRIMINATOR)
        );
    }
}
