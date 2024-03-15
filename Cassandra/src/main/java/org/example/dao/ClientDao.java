package org.example.dao;

import com.datastax.oss.driver.api.mapper.annotations.*;
import org.example.entity.Client;

@Dao
public interface ClientDao {

    @StatementAttributes(consistencyLevel = "LOCAL_QUORUM")
    @Select
    Client findById(int id);

    @StatementAttributes(consistencyLevel = "LOCAL_QUORUM")
    @Insert
    void save(Client client);

    @StatementAttributes(consistencyLevel = "LOCAL_QUORUM")
    @Delete(entityClass = Client.class)
    void deleteById(int id);
}
