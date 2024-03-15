package org.example.mapper;

import com.datastax.oss.driver.api.core.CqlIdentifier;
import com.datastax.oss.driver.api.mapper.annotations.DaoFactory;
import com.datastax.oss.driver.api.mapper.annotations.DaoKeyspace;
import com.datastax.oss.driver.api.mapper.annotations.DaoTable;
import com.datastax.oss.driver.api.mapper.annotations.Mapper;
import org.example.dao.CourtDao;

@Mapper
public interface CourtMapper {

    @DaoFactory
    CourtDao courtDao(@DaoKeyspace CqlIdentifier keyspace, @DaoTable String table);
}
