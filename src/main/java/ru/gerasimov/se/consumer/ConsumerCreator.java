package ru.gerasimov.se.consumer;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.stereotype.Component;
import ru.gerasimov.se.constants.MyConstants;

import java.util.Collections;
import java.util.Properties;

@Component
public class ConsumerCreator {

    private String groupId;

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Consumer<String, String> createConsumer(){
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, MyConstants.KAFKA_BROKERS);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        Consumer<String, String> consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Collections.singletonList(MyConstants.TOPIC_NAME));
        return consumer;
    }
}