package org.example.infrastructure.queue;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.KafkaFuture;

import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class KafkaManage {
    private final String bootstrapServers = "localhost:9092";  // Địa chỉ Kafka Broker

    private AdminClient createAdminClient() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", bootstrapServers);
        return AdminClient.create(properties);
    }
    public void createTopic(String topicName, int numPartitions, short replicationFactor) throws ExecutionException, InterruptedException {
        try (AdminClient adminClient = createAdminClient()) {
            NewTopic newTopic = new NewTopic(topicName, numPartitions, replicationFactor);
            KafkaFuture<Void> future = adminClient.createTopics(Collections.singleton(newTopic)).all();
            future.get();
            System.out.println("Topic created successfully: " + topicName);
        }
    }
    public boolean topicExists(String topicName) throws ExecutionException, InterruptedException {
        try (AdminClient adminClient = createAdminClient()) {
            return adminClient.listTopics().names().get().contains(topicName);
        }
    }

    // Tạo hoặc kiểm tra và tạo topic nếu chưa tồn tại
    public void ensureTopicExists(String topicName, int numPartitions, short replicationFactor) throws ExecutionException, InterruptedException {
        if (!topicExists(topicName)) {
            createTopic(topicName, numPartitions, replicationFactor);
        } else {
            System.out.println("Topic already exists: " + topicName);
        }
    }
}
