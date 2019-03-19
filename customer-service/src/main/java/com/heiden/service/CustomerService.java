package com.heiden.service;

import com.heiden.domain.Customer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayDeque;
import java.util.Deque;

@Service
public class CustomerService {
    public static final String TOPIC_NAME = "customer";
    public static final String GROUP_ID_CONFIG = "consumerGroup1";

    private Deque<Customer> customers = new ArrayDeque<>();

    @KafkaListener(
            topics = TOPIC_NAME,
            groupId = GROUP_ID_CONFIG,
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listen(Customer customer) {
        customers.add(customer);
    }

    public Customer getLatest() {
        return customers.peekLast();
    }
}
