package org.example.repository;

import com.datastax.oss.driver.api.core.CqlIdentifier;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.api.core.type.DataTypes;
import com.datastax.oss.driver.api.querybuilder.SchemaBuilder;
import com.datastax.oss.driver.api.querybuilder.schema.CreateKeyspace;
import org.example.names.ClientIds;
import org.example.names.CourtIds;
import org.example.names.RentIds;

import java.net.InetSocketAddress;

import static com.datastax.oss.driver.api.querybuilder.SchemaBuilder.createKeyspace;

public abstract class AbstractCassandraRepository implements AutoCloseable {

    private CqlSession session;

    public AbstractCassandraRepository() {
        initSession();
    }

    public void initSession() {
        session = CqlSession.builder()
                .addContactPoint(new InetSocketAddress("cassandra1", 9042))
                .addContactPoint(new InetSocketAddress("cassandra2", 9043))
                .withLocalDatacenter("dc1")
                .withAuthCredentials("cassandra", "cassandrapassword")
                .withKeyspace(CqlIdentifier.fromCql("rent_a_court"))
                .build();

        CreateKeyspace keyspace = createKeyspace(CqlIdentifier.fromCql("rent_a_court"))
                .ifNotExists()
                .withSimpleStrategy(2)
                .withDurableWrites(true);

        SimpleStatement createKeyspace = keyspace.build();
        session.execute(createKeyspace);

        // Create clients table
        SimpleStatement createClients = SchemaBuilder.createTable(CqlIdentifier.fromCql("clients"))
                .ifNotExists()
                .withPartitionKey(CqlIdentifier.fromCql(ClientIds.ID), DataTypes.INT)
                .withColumn(CqlIdentifier.fromCql(ClientIds.HAS_RENT), DataTypes.BOOLEAN)
                .withColumn(CqlIdentifier.fromCql(ClientIds.FIRST_NAME), DataTypes.TEXT)
                .withColumn(CqlIdentifier.fromCql(ClientIds.LAST_NAME), DataTypes.TEXT)
                .withColumn(CqlIdentifier.fromCql(ClientIds.PERSONAL_ID), DataTypes.TEXT)
                .build();
        session.execute(createClients);

        // Create rents table
        SimpleStatement createRents = SchemaBuilder.createTable(CqlIdentifier.fromCql("rents"))
                .ifNotExists()
                .withPartitionKey(CqlIdentifier.fromCql(RentIds.ID), DataTypes.INT)
                .withClusteringColumn(CqlIdentifier.fromCql(RentIds.CLIENT_ID), DataTypes.INT)
                .withClusteringColumn(CqlIdentifier.fromCql(RentIds.COURT_ID), DataTypes.INT)
                .withClusteringColumn(CqlIdentifier.fromCql(RentIds.START_TIME), DataTypes.DATE)
                .withColumn(CqlIdentifier.fromCql(RentIds.END_TIME), DataTypes.DATE)
                .build();
        session.execute(createRents);

        // Create courts table
        SimpleStatement createCourts = SchemaBuilder.createTable(CqlIdentifier.fromCql("courts"))
                .ifNotExists()
                .withPartitionKey(CqlIdentifier.fromCql(CourtIds.ID), DataTypes.INT)
                .withColumn(CqlIdentifier.fromCql(CourtIds.WIDTH), DataTypes.DOUBLE)
                .withColumn(CqlIdentifier.fromCql(CourtIds.LENGTH), DataTypes.DOUBLE)
                .withColumn(CqlIdentifier.fromCql(CourtIds.IS_RENTED), DataTypes.BOOLEAN)
                .withColumn(CqlIdentifier.fromCql(CourtIds.DISCRIMINATOR), DataTypes.TEXT)
                .withColumn(CqlIdentifier.fromCql(CourtIds.GOAL_WIDTH), DataTypes.DOUBLE)
                .withColumn(CqlIdentifier.fromCql(CourtIds.GOAL_LENGTH), DataTypes.DOUBLE)
                .withColumn(CqlIdentifier.fromCql(CourtIds.NET_WIDTH), DataTypes.DOUBLE)
                .withColumn(CqlIdentifier.fromCql(CourtIds.NET_LENGTH), DataTypes.DOUBLE)
                .withColumn(CqlIdentifier.fromCql(CourtIds.BASKET_HEIGHT), DataTypes.DOUBLE)
                .withColumn(CqlIdentifier.fromCql(CourtIds.BASKET_RADIUS), DataTypes.DOUBLE)
                .build();
        session.execute(createCourts);
    }

    public CqlSession getSession() {
        return this.session;
    }

    @Override
    public void close() {
        session.close();
    }
}
