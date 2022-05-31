package com.iwill.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerInterceptor;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

import java.util.Map;

public class ConsumerInterceptorOne implements ConsumerInterceptor<String, String> {

    public ConsumerRecords<String, String> onConsume(ConsumerRecords<String, String> records) {
        return null;
    }

    public void onCommit(Map<TopicPartition, OffsetAndMetadata> offsets) {

        System.out.println("1233");
    }

    public void close() {
        System.out.println("close");
    }

    public void configure(Map<String, ?> configs) {

    }
}
