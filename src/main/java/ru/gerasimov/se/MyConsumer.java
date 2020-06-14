package ru.gerasimov.se;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.gerasimov.se.consumer.ConsumerCreator;

import java.time.Duration;

@Component
public class MyConsumer extends Thread {

    private String groupId;
    @Autowired
    private ConsumerCreator consumerCreator;

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @Override
    public void run() {
        consumerCreator.setGroupId(groupId);
        Consumer<String, String> consumer = consumerCreator.createConsumer();
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(10));
            for (ConsumerRecord<String, String> record : records) {
                if(!record.value().startsWith(groupId)){
                    System.out.println(String.format(record.value()));
                }
            }
        }
    }
}
