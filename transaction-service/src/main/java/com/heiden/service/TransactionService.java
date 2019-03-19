package com.heiden.service;

import com.heiden.domain.CustomerBalance;
import com.heiden.domain.Transaction;
import com.heiden.kafka.KafkaConsumerConfig;
import org.apache.kafka.common.TopicPartition;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.ConsumerSeekAware;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.heiden.kafka.KafkaConsumerConfig.TOPIC_NAME;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Service
public class TransactionService implements ConsumerSeekAware {

    private boolean justRestarted = true;
    private List<Transaction> transactions = new ArrayList<>();

    @KafkaListener(
            topics = KafkaConsumerConfig.TOPIC_NAME,
            groupId = KafkaConsumerConfig.GROUP_ID_CONFIG,
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listen(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<CustomerBalance> getCustomerBalances() {
        return transactions
                .stream()
                .collect(
                        groupingBy(Transaction::getCustomer,
                                Collectors.summingDouble(c -> c.getChange().doubleValue())
                        )
                )
                .entrySet()
                .stream()
                .map(e -> new CustomerBalance(
                                e.getKey(),
                                new BigDecimal(e.getValue()).setScale(2, RoundingMode.HALF_UP)
                        )
                )
                .collect(toList());
    }

    @Override
    public void registerSeekCallback(ConsumerSeekAware.ConsumerSeekCallback callback) {
        callback.seek(TOPIC_NAME, 0, 0);
    }

    @Override
    public void onPartitionsAssigned(Map<TopicPartition, Long> assignments, ConsumerSeekAware.ConsumerSeekCallback callback) {
        callback.seek(TOPIC_NAME, 0, 0);
    }

    @Override
    public void onIdleContainer(Map<TopicPartition, Long> assignments, ConsumerSeekAware.ConsumerSeekCallback callback) {
        callback.seek(TOPIC_NAME, 0, 0);
    }

}
