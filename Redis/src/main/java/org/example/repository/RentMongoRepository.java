package org.example.repository;

import com.mongodb.client.ClientSession;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.example.entityMgd.ClientMgd;
import org.example.entityMgd.CourtMgd;
import org.example.entityMgd.RentMgd;
import org.example.mapper.RentMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.inc;
import static com.mongodb.client.model.Updates.set;

public class RentMongoRepository extends AbstractMongoRepository implements Repository<RentMgd>{

    private final MongoCollection<RentMgd> rents = getDatabase().getCollection("rents", RentMgd.class);
    private final MongoCollection<Document> docRents = getDatabase().getCollection("rents");
    private final MongoCollection<ClientMgd> clients = getDatabase().getCollection("clients", ClientMgd.class);
    private final MongoCollection<CourtMgd> courts = getDatabase().getCollection("courts", CourtMgd.class);

    @Override
    public boolean add(RentMgd rent) {
        ClientSession clientSession = getMongoClient().startSession();
        try {
            clientSession.startTransaction();
            clients.updateOne(clientSession, eq("_id", rent.getClient().getId()), inc("has_rent", 1));
            courts.updateOne(clientSession, eq("_id", rent.getCourt().getId()), inc("is_rented", 1));
            rent.getClient().setHasRent(1);
            rent.getCourt().setIsRented(1);
            rents.insertOne(clientSession, rent);
            clientSession.commitTransaction();
            return true;
        } catch (Exception e) {
            clientSession.abortTransaction();
            return false;
        } finally {
            clientSession.close();
        }
    }

    @Override
    public boolean remove(int id) {
        try {
            rents.deleteOne(eq("_id", id));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public RentMgd find(int id) {
        try {
            Document rent = docRents.find(eq("_id", id)).first();
            return rent != null ? RentMapper.toRentMgd(rent) : null;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean update(RentMgd rent) {
        try {
            rents.replaceOne(eq("_id", rent.getId()), rent);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<RentMgd> findAll() {
        try {
            FindIterable<Document> documentRents = docRents.find();

            List<RentMgd> mongoRents = new ArrayList<>();

            for (Document rent : documentRents) {
                mongoRents.add(RentMapper.toRentMgd(rent));
            }
            return mongoRents;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean endRent(RentMgd rent) {
        ClientSession clientSession = getMongoClient().startSession();
        try {
            clientSession.startTransaction();
            clients.updateOne(clientSession, eq("_id", rent.getClient().getId()), set("has_rent", 0));
            courts.updateOne(clientSession, eq("_id", rent.getCourt().getId()), set("is_rented", 0));
            rents.updateOne(clientSession, eq("_id", rent.getId()), set("end_time", LocalDateTime.now()));
            clientSession.commitTransaction();
            return true;
        } catch (Exception e) {
            clientSession.abortTransaction();
            return false;
        } finally {
            clientSession.close();
        }
    }

    @Override
    public void close() {
        super.close();
    }
}
