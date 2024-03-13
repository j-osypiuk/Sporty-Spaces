package org.example.repository;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.example.entityMgd.CourtMgd;
import org.example.mapper.CourtMapper;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class CourtRepository extends AbstractMongoRepository implements Repository<CourtMgd>{

    private final MongoCollection<CourtMgd> courts = getDatabase().getCollection("courts", CourtMgd.class);
    private final MongoCollection<Document> docCourts = getDatabase().getCollection("courts");


    @Override
    public boolean add(CourtMgd court) {
        try {
            courts.insertOne(court);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean remove(int id) {
        try {
            courts.deleteOne(eq("_id", id));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public CourtMgd find(int id) {
        try {
            Document court = docCourts.find(eq("_id", id)).first();
            return court != null ? CourtMapper.toCourtMgd(court) : null;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean update(CourtMgd court) {
        try {
            courts.replaceOne(eq("_id", court.getId()), court);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<CourtMgd> findAll() {
        try {
            FindIterable<Document> documentCourts = docCourts.find();

            List<CourtMgd> mongoCourts = new ArrayList<>();

            for (Document court : documentCourts) {
                mongoCourts.add(CourtMapper.toCourtMgd(court));
            }
            return mongoCourts;
        } catch (Exception e) {
            return null;
        }
    }
}
