package org.example.queryProvider;

import com.datastax.oss.driver.api.core.CqlIdentifier;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.BatchStatement;
import com.datastax.oss.driver.api.core.cql.BatchType;
import com.datastax.oss.driver.api.mapper.MapperContext;
import com.datastax.oss.driver.api.querybuilder.QueryBuilder;
import com.datastax.oss.driver.api.querybuilder.insert.Insert;
import com.datastax.oss.driver.api.querybuilder.relation.Relation;
import com.datastax.oss.driver.api.querybuilder.update.Update;
import org.example.entity.Rent;
import org.example.names.ClientIds;
import org.example.names.CourtIds;
import org.example.names.RentIds;

import java.time.LocalDate;

import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.literal;

public class RentQueryProvider {
    private final CqlSession session;


    public RentQueryProvider(MapperContext ctx) {
        this.session = ctx.getSession();
    }

    public void save(Rent rent) {
        Insert addRent = QueryBuilder.insertInto(CqlIdentifier.fromCql("rents"))
                .value(RentIds.ID, literal(rent.getId()))
                .value(RentIds.COURT_ID, literal(rent.getCourtId()))
                .value(RentIds.CLIENT_ID, literal(rent.getClientId()))
                .value(RentIds.START_TIME, literal(rent.getStartTime()))
                .value(RentIds.END_TIME, literal(null));

        Update updateClient = QueryBuilder.update(CqlIdentifier.fromCql("clients"))
                        .setColumn(ClientIds.HAS_RENT, literal(true))
                        .where(Relation.column(ClientIds.ID).isEqualTo(literal(rent.getClientId())));

        Update updateCourt = QueryBuilder.update(CqlIdentifier.fromCql("courts"))
                .setColumn(CourtIds.IS_RENTED, literal(true))
                .where(Relation.column(ClientIds.ID).isEqualTo(literal(rent.getCourtId())));

        BatchStatement batchStatement = BatchStatement.builder(BatchType.LOGGED)
                .addStatement(updateClient.build())
                .addStatement(updateCourt.build())
                .addStatement(addRent.build())
                .build();

        session.execute(batchStatement);
    }

    public void end(Rent rent) {
        Update endRent = QueryBuilder.update(CqlIdentifier.fromCql("rents"))
                .setColumn(RentIds.END_TIME, literal(LocalDate.now()))
                .where(Relation.column(RentIds.ID).isEqualTo(literal(rent.getId())))
                .where(Relation.column(RentIds.COURT_ID).isEqualTo(literal(rent.getCourtId())))
                .where(Relation.column(RentIds.CLIENT_ID).isEqualTo(literal(rent.getClientId())))
                .where(Relation.column(RentIds.START_TIME).isEqualTo(literal(rent.getStartTime())));

        Update updateClient = QueryBuilder.update(CqlIdentifier.fromCql("clients"))
                .setColumn(ClientIds.HAS_RENT, literal(false))
                .where(Relation.column(ClientIds.ID).isEqualTo(literal(rent.getClientId())));

        Update updateCourt = QueryBuilder.update(CqlIdentifier.fromCql("courts"))
                .setColumn(CourtIds.IS_RENTED, literal(false))
                .where(Relation.column(ClientIds.ID).isEqualTo(literal(rent.getCourtId())));

        BatchStatement batchStatement = BatchStatement.builder(BatchType.LOGGED)
                .addStatement(updateClient.build())
                .addStatement(updateCourt.build())
                .addStatement(endRent.build())
                .build();

        session.execute(batchStatement);
    }
}
