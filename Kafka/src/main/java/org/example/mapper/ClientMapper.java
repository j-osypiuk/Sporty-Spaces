package org.example.mapper;

import org.bson.Document;
import org.example.entity.Client;
import org.example.entityMgd.ClientMgd;

public class ClientMapper {

    public static final String ID = "_id";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String PERSONAL_ID = "personal_ID";
    public static final String HAS_RENT = "has_rent";

    public static Client fromMongoClient(ClientMgd client) {
        return new Client(
                client.getId(),
                client.getFirstName(),
                client.getLastName(),
                client.getPersonalID(),
                client.getHasRent()
        );
    }

    public static ClientMgd toMongoClient(Client client) {
        return new ClientMgd(
                client.getId(),
                client.getFirstName(),
                client.getLastName(),
                client.getPersonalID(),
                client.getHasRent()
        );
    }

    public static ClientMgd toClientMgd(Document clientDocument) {
        return new ClientMgd(
                clientDocument.get(ID, Integer.class),
                clientDocument.get(FIRST_NAME, String.class),
                clientDocument.get(LAST_NAME, String.class),
                clientDocument.get(PERSONAL_ID, String.class),
                clientDocument.get(HAS_RENT, Integer.class)
        );
    }
}
