package org.example.repository;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import redis.clients.jedis.DefaultJedisClientConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisClientConfig;
import redis.clients.jedis.JedisPooled;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public abstract class AbstractRedisRepository {

    private static JedisPooled jedis;
    public AbstractRedisRepository() {
        initConnection();
    }
    public void initConnection()  {
        JSONParser parser = new JSONParser();

        try (Reader reader = new FileReader("redisconf.json")) {

            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            String host = (String) jsonObject.get("host");
            String port = (String) jsonObject.get("port");

            JedisClientConfig clientConfig = DefaultJedisClientConfig.builder().build();
            jedis = new JedisPooled(new HostAndPort(host, Integer.parseInt(port)), clientConfig);

        } catch (IOException | ParseException | JedisConnectionException e) {
            throw new RuntimeException(e);
        }
    }

    public JedisPooled getJedis() {
        return jedis;
    }
}
