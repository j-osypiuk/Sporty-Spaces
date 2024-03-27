package org.example.mapper;

import org.bson.Document;
import org.example.entity.Rent;
import org.example.entityKafka.RentKfk;
import org.example.entityMgd.RentMgd;

import java.time.LocalDateTime;
import java.util.Date;

public class RentMapper {

    public static final String ID = "_id";
    public static final String CLIENT = "client";
    public static final String COURT = "court";
    public static final String START_TIME = "start_time";
    public static final String END_TIME = "end_time";

    public static Rent fromMongoRent(RentMgd rentMgd) {
        return new Rent(
                rentMgd.getId(),
                CourtMapper.fromMongoCourt(rentMgd.getCourt()),
                ClientMapper.fromMongoClient(rentMgd.getClient()),
                rentMgd.getStartTime(),
                rentMgd.getEndTime()
        );
    }

    public static RentMgd toMongoRent(Rent rent) {
        return new RentMgd(
                rent.getId(),
                CourtMapper.toMongoCourt(rent.getCourt()),
                ClientMapper.toMongoClient(rent.getClient()),
                rent.getStartTime(),
                rent.getEndTime()
        );
    }

    public static RentMgd toRentMgd(Document rentDocument) {
        return new RentMgd(
                rentDocument.get(ID, Integer.class),
                CourtMapper.toCourtMgd((Document) rentDocument.get(COURT)),
                ClientMapper.toClientMgd((Document) rentDocument.get(CLIENT)),
                rentDocument.get(START_TIME, Date.class),
                rentDocument.get(END_TIME, Date.class)
        );
    }

    public static RentKfk toRentKfk(RentMgd rentMgd) {
        return new RentKfk(
                rentMgd.getId(),
                "lodz_rental",
                rentMgd.getCourt().getId(),
                rentMgd.getClient().getId(),
                rentMgd.getStartTime(),
                rentMgd.getEndTime()
        );
    }
}
