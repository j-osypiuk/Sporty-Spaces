package org.example.repository;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import org.example.entity.BasketballCourt;
import org.example.entity.Court;
import org.example.entity.FootballCourt;
import org.example.entity.VolleyballCourt;
import redis.clients.jedis.params.SetParams;

public class RedisCourtRepository extends AbstractRedisRepository {

    private final Jsonb jsonb = JsonbBuilder.create();
    private final static String hashPrefix = "Court:";

    public RedisCourtRepository() {
        super();
    }

    public boolean addJson(Court court) {
        try {
            String stringCourt = jsonb.toJson(court);
            super.getJedis().set(hashPrefix + court.getId(), stringCourt, SetParams.setParams().ex(180));

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Court findJson(int id) {
        try {
            String stringCourt =  super.getJedis().get(hashPrefix + id);

            if (stringCourt == null) {
                return null;
            }

            if(stringCourt.contains("goal")) {
                return jsonb.fromJson(stringCourt, FootballCourt.class);
            }
            if(stringCourt.contains("net")) {
                return jsonb.fromJson(stringCourt, VolleyballCourt.class);
            }
            if(stringCourt.contains("basket")) {
                return jsonb.fromJson(stringCourt, BasketballCourt.class);
            }
            return null;

        } catch (Exception e) {
            return null;
        }
    }

    public boolean deleteJson(int id) {
        try {
            super.getJedis().del(hashPrefix + id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteAllJson() {
        try {
            super.getJedis().flushAll();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
