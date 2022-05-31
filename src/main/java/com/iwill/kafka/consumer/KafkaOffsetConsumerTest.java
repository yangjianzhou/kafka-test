package com.iwill.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerGroupMetadata;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

public class KafkaOffsetConsumerTest {


    public static final String BROKER_LIST = "localhost:9092";

    public static final String TOPIC = "__consumer_offsets";

    public static final String GROUP_ID = "offset";

    public static final AtomicBoolean IS_RUNNING = new AtomicBoolean(true);

    public static Properties initConfig() {
        Properties props = new Properties();
        props.put("bootstrap.servers", BROKER_LIST);
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("group.id", GROUP_ID);
        props.put("client.id", "consumer.client.id.demo");
      //  props.put(ConsumerConfig.INTERCEPTOR_CLASSES_CONFIG,ConsumerInterceptorOne.class.getName());
        return props;
    }

    public static void main(String[] args) {
        Properties props = initConfig();
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
        consumer.subscribe(Arrays.asList(TOPIC));

        try{
            while (IS_RUNNING.get()){
                ConsumerRecords<String,String> records = consumer.poll(Duration.ofMillis(1000));
                for(ConsumerRecord<String,String> record : records){
                    //System.out.println("topic = " + record.topic() + " ,partition = " + record.partition() +" , offset = " + record.offset() + " , key = "+ record.key() +" , value = " + record.value());
                    System.out.println(record);
                }
            }
        }catch (Exception exp){
            exp.printStackTrace();
        }finally {
            consumer.close();
        }
    }
}
