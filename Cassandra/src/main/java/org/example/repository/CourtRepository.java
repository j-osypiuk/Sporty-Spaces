package org.example.repository;

import com.datastax.oss.driver.api.core.CqlIdentifier;
import org.example.dao.CourtDao;
import org.example.entity.Court;
import org.example.mapper.CourtMapper;
import org.example.mapper.CourtMapperBuilder;


public class CourtRepository extends AbstractCassandraRepository implements Repository<Court>{

    private CourtDao courtDao;

    public CourtRepository() {
        CourtMapper courtMapper = new CourtMapperBuilder((super.getSession())).build();
        courtDao = courtMapper.courtDao(CqlIdentifier.fromCql("rent_a_court"), "courts");
    }

    public boolean add(Court court) {
        try {
            courtDao.save(court);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean remove(int id) {
        try {
            courtDao.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Court find(int id) {
        try {
            return courtDao.findById(id);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean update(Court court) {
        try {
            courtDao.save(court);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void close() {
        super.close();
    }
}
