package com.iwill.kafka.producer;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

public class ProducerInterceptorOne implements ProducerInterceptor {

    public ProducerRecord onSend(ProducerRecord record) {
        //System.out.println("intercept before send");
        return record;
    }

    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {

        //System.out.println("on ack");
    }

    public void close() {

    }

    public void configure(Map<String, ?> configs) {

    }
}
