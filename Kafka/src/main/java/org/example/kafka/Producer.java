package org.example.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.admin.Admin;
import org.apache.kafka.clients.admin.CreateTopicsOptions;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.KafkaFuture;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.serialization.StringSerializer;
import org.example.entityKafka.RentKfk;

import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class Producer implements AutoCloseable{
    private KafkaProducer<String, String> producer;

    public void initProducer() {
        Properties producerConfig = new Properties();

        producerConfig.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        producerConfig.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        producerConfig.put(ProducerConfig.CLIENT_ID_CONFIG, "local");
        producerConfig.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka1:9192,kafka2:9292,kafka3:9392");
        producerConfig.put(ProducerConfig.ACKS_CONFIG, "all");
        producerConfig.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);


        producer = new KafkaProducer<>(producerConfig);
    }

    public void createTopic() throws ExecutionException, InterruptedException {
        Properties properties = new Properties();

        int partitionNumber = 3;
        short replicationFactor = 3;

        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka1:9192,kafka2:9292,kafka3:9392");

        try (Admin admin = Admin.create(properties)) {
            NewTopic newTopic = new NewTopic("court_rent", partitionNumber, replicationFactor);
            CreateTopicsOptions options = new CreateTopicsOptions()
                    .timeoutMs(1000)
                    .validateOnly(false)
                    .retryOnQuotaViolation(true);
            CreateTopicsResult result = admin.createTopics(Collections.singletonList(newTopic), options);
            KafkaFuture<Void> futureResult = result.values().get("court_rent");
            futureResult.get();
        } catch (ExecutionException ee) {
            System.out.println(ee.getCause());
        }
    }

    public void sendRentToKafka(RentKfk rentKfk) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {

            String rentKfkJson = objectMapper.writeValueAsString(rentKfk);

            List<PartitionInfo> partitions = producer.partitionsFor("court_rent");
            int i=0;

            for (PartitionInfo partition : partitions) {
                String key = "partition_" + partition.partition();
                System.out.println("Sending record to: " + key);
                ProducerRecord<String, String> record = new ProducerRecord<>("court_rent", i, key, rentKfkJson);
                producer.send(record);
                i++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        producer.close();
    }
}


