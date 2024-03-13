package org.example.mapper;

import org.bson.Document;
import org.example.entity.BasketballCourt;
import org.example.entity.FootballCourt;
import org.example.entity.VolleyballCourt;
import org.example.entityMgd.BasketballCourtMgd;
import org.example.entityMgd.CourtMgd;
import org.example.entityMgd.FootballCourtMgd;
import org.example.entityMgd.VolleyballCourtMgd;
import org.example.entity.Court;

public class CourtMapper {

    private static final String ID = "_id";
    private static final String WIDTH = "width";
    private static final String LENGTH = "length";
    private static final String IS_RENTED = "is_rented";
    private static final String NET_LENGTH = "net_length";
    private static final String NET_WIDTH = "net_width";
    private static final String GOAL_WIDTH = "goal_width";
    private static final String GOAL_LENGTH = "goal_length";
    private static final String BASKET_HEIGTH = "basket_height";
    private static final String BASKET_RADIUS = "basket_radius";

    public static CourtMgd toMongoCourt(Court court) {
        if (court instanceof FootballCourt)
            return toMongoFootballCourt((FootballCourt) court);
        if (court instanceof BasketballCourt)
            return toMongoBasketballCourt((BasketballCourt) court);
        if (court instanceof VolleyballCourt)
            return toMongoVolleyballCourt((VolleyballCourt) court);
        return null;
    }

    public static Court fromMongoCourt(CourtMgd court) {
        if (court instanceof FootballCourtMgd)
            return fromMongoFootballCourt((FootballCourtMgd) court);
        if (court instanceof BasketballCourtMgd)
            return fromMongoBasketballCourt((BasketballCourtMgd) court);
        if (court instanceof VolleyballCourtMgd)
            return fromMongoVolleyballCourt((VolleyballCourtMgd) court);
        return null;
    }



   private static FootballCourt fromMongoFootballCourt(FootballCourtMgd footballCourt) {
        return new FootballCourt(
                footballCourt.getId(),
                footballCourt.getWidth(),
                footballCourt.getLength(),
                footballCourt.getIsRented(),
                footballCourt.getGoalLength(),
                footballCourt.getGoalWidth()
        );
    }

    private static BasketballCourt fromMongoBasketballCourt(BasketballCourtMgd basketballCourt) {
        return new BasketballCourt(
                basketballCourt.getId(),
                basketballCourt.getWidth(),
                basketballCourt.getLength(),
                basketballCourt.getIsRented(),
                basketballCourt.getBasketHeight(),
                basketballCourt.getBasketRadius()
        );
    }

    private static VolleyballCourt fromMongoVolleyballCourt(VolleyballCourtMgd volleyballCourt) {
        return new VolleyballCourt(
                volleyballCourt.getId(),
                volleyballCourt.getWidth(),
                volleyballCourt.getLength(),
                volleyballCourt.getIsRented(),
                volleyballCourt.getNetLength(),
                volleyballCourt.getWidth()
        );
    }

    private static BasketballCourtMgd toMongoBasketballCourt(BasketballCourt court) {
            return new BasketballCourtMgd(
                    court.getId(),
                    court.getWidth(),
                    court.getLength(),
                    court.getIsRented(),
                    court.getBasketHeight(),
                    court.getBasketRadius()
            );
    }

    private static FootballCourtMgd toMongoFootballCourt(FootballCourt court) {
        return new FootballCourtMgd(
                court.getId(),
                court.getWidth(),
                court.getLength(),
                court.getIsRented(),
                court.getGoalWidth(),
                court.getGoalLength()
        );
    }

    private static VolleyballCourtMgd toMongoVolleyballCourt(VolleyballCourt court) {
        return new VolleyballCourtMgd(
                court.getId(),
                court.getWidth(),
                court.getLength(),
                court.getIsRented(),
                court.getNetLength(),
                court.getNetWidth()
        );
    }

    public static CourtMgd toCourtMgd(Document courtDocument) {
        if (courtDocument.get("_clazz").equals("football"))
            return toFootballCourtMgd(courtDocument);
        if (courtDocument.get("_clazz").equals("basketball"))
            return toBasketballCourtMng(courtDocument);
        if (courtDocument.get("_clazz").equals("volleyball"))
            return toVolleyballCourtMng(courtDocument);
        return null;
    }

    private static FootballCourtMgd toFootballCourtMgd(Document courtDocument) {
        return new FootballCourtMgd(
                courtDocument.get(ID, Integer.class),
                courtDocument.get(WIDTH, Double.class),
                courtDocument.get(LENGTH, Double.class),
                courtDocument.get(IS_RENTED, Integer.class),
                courtDocument.get(GOAL_WIDTH, Double.class),
                courtDocument.get(GOAL_LENGTH, Double.class)
        );
    }

    private static BasketballCourtMgd toBasketballCourtMng(Document courtDocument) {
        return new BasketballCourtMgd(
                courtDocument.get(ID, Integer.class),
                courtDocument.get(WIDTH, Double.class),
                courtDocument.get(LENGTH, Double.class),
                courtDocument.get(IS_RENTED, Integer.class),
                courtDocument.get(BASKET_HEIGTH, Double.class),
                courtDocument.get(BASKET_RADIUS, Double.class)
        );
    }

    private static VolleyballCourtMgd toVolleyballCourtMng(Document courtDocument) {
        return new VolleyballCourtMgd(
                courtDocument.get(ID, Integer.class),
                courtDocument.get(WIDTH, Double.class),
                courtDocument.get(LENGTH, Double.class),
                courtDocument.get(IS_RENTED, Integer.class),
                courtDocument.get(NET_LENGTH, Double.class),
                courtDocument.get(NET_WIDTH, Double.class)
        );
    }
}
