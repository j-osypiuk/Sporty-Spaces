package org.example.manager;

import org.example.entity.Court;
import org.example.entityMgd.CourtMgd;
import org.example.mapper.CourtMapper;
import org.example.repository.CourtMongoRepository;
import org.example.repository.RedisCourtRepository;

import java.util.ArrayList;
import java.util.List;

public class CourtManager {

    private final CourtMongoRepository courtMongoRepository = new CourtMongoRepository();
    private final RedisCourtRepository redisCourtRepository = new RedisCourtRepository();

    public boolean addCourt(Court court) {
        return courtMongoRepository.add(CourtMapper.toMongoCourt(court));
    }

    public boolean removeCourt(int id) {
        boolean isRemoved = courtMongoRepository.remove(id);
        if (isRemoved)
            redisCourtRepository.deleteJson(id);

        return isRemoved;
    }

    public Court findCourt(int id) {
            Court redisCourt = redisCourtRepository.findJson(id);
            if (redisCourt == null) {
                Court mongoCourt = CourtMapper.fromMongoCourt(courtMongoRepository.find(id));
                if (mongoCourt != null) {
                    redisCourtRepository.addJson(mongoCourt);
                    return mongoCourt;
                }
            }
            return redisCourt;
    }

    public boolean updateCourt(Court court) {
        boolean isUpdated = courtMongoRepository.update(CourtMapper.toMongoCourt(court));
        if (isUpdated) {
            redisCourtRepository.deleteJson(court.getId());
            redisCourtRepository.addJson(court);
        }
        return isUpdated;
    }

    public List<Court> findAllCourts() {
        List<CourtMgd> mongoCourts = courtMongoRepository.findAll();

        List<Court> courts = new ArrayList<>();

        for (CourtMgd courtMgd : mongoCourts) {
            courts.add(CourtMapper.fromMongoCourt(courtMgd));
        }
        return courts;
    }
}
