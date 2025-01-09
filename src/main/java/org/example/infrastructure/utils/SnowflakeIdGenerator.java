package org.example.infrastructure.utils;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class SnowflakeIdGenerator implements IdentifierGenerator {
    private static final long EPOCH = 1627776000000L;
    private static final long SERVER_ID = 1L;

    private static final long SEQUENCE_BITS = 12L;
    private static final long SERVER_ID_BITS = 10L;

    private static final long MAX_SERVER_ID = ~(-1L << SERVER_ID_BITS);
    private static final long MAX_SEQUENCE = ~(-1L << SEQUENCE_BITS);

    private static final long SERVER_ID_SHIFT = SEQUENCE_BITS;
    private static final long TIMESTAMP_SHIFT = SEQUENCE_BITS + SERVER_ID_BITS;

    private final AtomicLong sequence = new AtomicLong(0L);
    private long lastTimestamp = -1L;

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        long timestamp = timeGen();

        if (timestamp < lastTimestamp) {
            throw new RuntimeException("Clock moved backwards. Refusing to generate id");
        }

        if (lastTimestamp == timestamp) {
            long seq = (sequence.incrementAndGet()) & MAX_SEQUENCE;
            if (seq == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence.set(0L);
        }

        lastTimestamp = timestamp;

        return ((timestamp - EPOCH) << TIMESTAMP_SHIFT)
                | (SERVER_ID << SERVER_ID_SHIFT)
                | sequence.get();
    }
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }
    private long timeGen() {
        return System.currentTimeMillis();
    }
}
