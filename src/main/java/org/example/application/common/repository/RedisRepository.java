package org.example.application.common.repository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface RedisRepository {
    void setWithTimeExpire(String key, String value, long expireInSeconds);
    void set(String key, String value);

    void setTimeToLive(String key, long timeOutInDays);

    void hashSet(String key, String field, Object value);

    boolean hashExits(String key, String field);

    String get(String key);

    String hashGet(String key, String value);

    List<String> hashGetByFieldPrefix(String key, String fieldPrefix);

    Set<String> getFieldPrefix(String key);

    void delete(String key);

    void deleteFieldInHash(String key, String field);

    void deleteManyFieldInHash(String key, List<String> fields);
    void LPush(String list, String value);
    String RPop(String list);
    long getListSize(String list);
}
