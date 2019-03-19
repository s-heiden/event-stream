package com.heiden.kafka;

import com.heiden.domain.Transaction;
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

    public static final String KAFKA_BROKERS = "localhost:9092";
    public static final String TOPIC_NAME = "transaction";
    public static final String GROUP_ID_CONFIG = "consumerGroup1";
    public static final String EARLIEST = "earliest";


    @Bean
    public ConsumerFactory<Long, Transaction> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_BROKERS);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID_CONFIG);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, EARLIEST);

        return new DefaultKafkaConsumerFactory<>(
                props,
                new LongDeserializer(),
                new JsonDeserializer<>(Transaction.class)
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<Long, Transaction>
    kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<Long, Transaction> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
