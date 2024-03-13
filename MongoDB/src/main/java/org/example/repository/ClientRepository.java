package org.example.repository;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.example.entityMgd.ClientMgd;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class ClientRepository extends AbstractMongoRepository implements Repository<ClientMgd> {

    private final MongoCollection<ClientMgd> clients = getDatabase().getCollection("clients", ClientMgd.class);

    @Override
    public boolean add(ClientMgd client) {
        try {
            clients.insertOne(client);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean remove(int id) {
        try {
            clients.deleteOne(eq("_id", id));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public ClientMgd find(int id) {
        try {
            return clients.find(eq("_id", id)).first();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean update(ClientMgd client) {
        try {
            clients.replaceOne(eq("_id", client.getId()), client);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<ClientMgd> findAll() {
        try {
            FindIterable<ClientMgd> mongoClientsMgd = clients.find();

            List<ClientMgd> mongoClients = new ArrayList<>();

            for (ClientMgd client : mongoClientsMgd) {
                mongoClients.add(client);
            }
            return mongoClients;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void close() {
        super.close();
    }
}
