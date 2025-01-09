package org.example.infrastructure.repository.implement;

import lombok.AllArgsConstructor;
import org.example.application.common.repository.RedisRepository;
import org.example.application.user.exception.InternalServerError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Repository
@AllArgsConstructor
public class RedisRepositoryImpl implements RedisRepository {
    private final Logger logger = LoggerFactory.getLogger(RedisRepositoryImpl.class);
    private final RedisTemplate<String, String> redisTemplate;
    private final HashOperations<String, String, Object> hashOperations;
    private final ListOperations<String, String> listOperations;

    @Override
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void setWithTimeExpire(String key, String value, long expireInSeconds) {
        redisTemplate.opsForValue().set(key, value, Duration.ofSeconds(expireInSeconds));
    }

    @Override
    public void setTimeToLive(String key, long timeOutInDays) {
        redisTemplate.expire(key, timeOutInDays, TimeUnit.DAYS);
    }

    @Override
    public void hashSet(String key, String field, Object value) {
        hashOperations.put(key, field, value);
    }

    @Override
    public boolean hashExits(String key, String field) {
        return hashOperations.hasKey(key, field);
    }

    @Override
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public String hashGet(String key, String field) {
        return Objects.requireNonNull(hashOperations.get(key, field)).toString();
    }

    @Override
    public List<String> hashGetByFieldPrefix(String key, String fieldPrefix) {
        List<String> results = new ArrayList<>();
        Map<String, Object> entries = hashOperations.entries(key);

        for (Map.Entry<String, Object> entry : entries.entrySet()) {
            if (entry.getKey().startsWith(fieldPrefix)) {
                results.add(entry.getValue().toString());
            }
        }
        return results;
    }

    @Override
    public Set<String> getFieldPrefix(String key) {
        return hashOperations.entries(key).keySet();
    }

    @Override
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public void deleteFieldInHash(String key, String field) {
        hashOperations.delete(key, field);
    }

    @Override
    public void deleteManyFieldInHash(String key, List<String> fields) {
        for (String s : fields) {
            hashOperations.delete(key, s);
        }
    }

    @Override
    public void LPush(String list, String value) {
        try {
            listOperations.leftPush(list, value);
        } catch (Exception e) {
            logger.error("Error to save key {} to list with error {}", value, e.getMessage());
            throw new InternalServerError("Error to save key");
        }
    }

    @Override
    public String RPop(String list) {
        try {
            return listOperations.rightPop(list);
        } catch (Exception e) {
            logger.error("Error to right pop {} with list {}", e.getMessage(), list);
            throw new InternalServerError("Error to right pop in redis");
        }
    }

    @Override
    public long getListSize(String list) {
        Long size = listOperations.size(list);
        return (size != null) ? size : 0L;
    }
}
