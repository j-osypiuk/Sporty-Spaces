package org.example.dao;

import com.datastax.oss.driver.api.mapper.annotations.*;
import org.example.entity.Rent;
import org.example.queryProvider.RentQueryProvider;

@Dao
public interface RentDao {

    @StatementAttributes(consistencyLevel = "LOCAL_QUORUM")
    @Select
    Rent findById(int id);

    @StatementAttributes(consistencyLevel = "LOCAL_QUORUM")
    @QueryProvider(providerClass = RentQueryProvider.class)
    void save(Rent rent);

    @StatementAttributes(consistencyLevel = "LOCAL_QUORUM")
    @QueryProvider(providerClass = RentQueryProvider.class)
    void end(Rent rent);

    @StatementAttributes(consistencyLevel = "LOCAL_QUORUM")
    @Delete(entityClass = Rent.class)
    void deleteById(int id);
}
