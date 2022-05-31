package com.iwill.kafka.producer;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;
import java.util.concurrent.Future;

public class KafkaProducerTest {

    public static final String BROKER_LIST = "localhost:9092";

    public static final String TOPIC = "topic-demo";

    public static Properties initConfig() {
        Properties props = new Properties();
        props.put("bootstrap.servers", BROKER_LIST);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("client.id", "producer.client.id.demo");
        props.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, ProducerInterceptorOne.class.getName());
        return props;
    }

    public static void main(String[] args) throws Exception {
        Properties props = initConfig();
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);
        for (int i = 0; i < 1000; i++) {
            ProducerRecord<String, String> record = new ProducerRecord<String, String>(TOPIC, null, "hello,kafka");
            try {
                Future<RecordMetadata> future = producer.send(record, new Callback() {
                    public void onCompletion(RecordMetadata metadata, Exception exception) {
                        if (exception != null) {
                            exception.printStackTrace();
                        } else {
                            System.out.println(metadata.topic() + " - " + metadata.partition() + "-" + metadata.offset());
                        }
                    }
                });
                //future.get();
                Thread.sleep(1000L);
            } catch (Exception exp) {
                exp.printStackTrace();
            }
        }
        System.in.read();
    }

}
