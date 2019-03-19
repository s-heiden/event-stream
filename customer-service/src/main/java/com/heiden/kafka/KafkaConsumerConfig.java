package com.heiden.kafka;

import com.heiden.domain.Customer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    private static String KAFKA_BROKERS = "localhost:9092";
    private static String TOPIC_NAME = "customer";
    private static String GROUP_ID_CONFIG = "consumerGroup1";

    @Bean
    public ConsumerFactory<Long, Customer> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_BROKERS);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID_CONFIG);
        return new DefaultKafkaConsumerFactory<>(
                props,
                new LongDeserializer(),
                new JsonDeserializer<>(Customer.class)
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<Long, Customer>
    kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<Long, Customer> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
