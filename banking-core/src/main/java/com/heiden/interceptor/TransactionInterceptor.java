package com.heiden.interceptor;

import com.heiden.domain.Transaction;
import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;
import java.util.Random;

public class TransactionInterceptor implements ProducerInterceptor<Long, Transaction> {

    private Random random = new Random();

    @Override
    public ProducerRecord<Long, Transaction> onSend(ProducerRecord<Long, Transaction> record) {
        double fraudPercentage = 0.1;
        record.value().setFraud(random.nextDouble() >= (1. - fraudPercentage));
        return record;
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
    }

    @Override
    public void close() {
    }

    @Override
    public void configure(Map<String, ?> configs) {
    }

}
