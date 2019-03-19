package com.heiden;

import com.heiden.domain.Customer;
import com.heiden.domain.CustomerFactory;
import com.heiden.domain.Transaction;
import com.heiden.domain.TransactionFactory;
import com.heiden.kafka.CustomerKafkaProducerFactory;
import com.heiden.kafka.TransactionKafkaProducerFactory;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.concurrent.ExecutionException;

public class Application {


    public static void main(String[] args) {
        runProducer();
    }

    private static void runProducer() {

        Producer<Long, Customer> customerProducer = CustomerKafkaProducerFactory.createProducer();
        Producer<Long, Transaction> transactionProducer = TransactionKafkaProducerFactory.createTransaction();

        while (true) {
            try {
                customerProducer.send(new ProducerRecord<>(
                                Customer.class.getSimpleName().toLowerCase(),
                                CustomerFactory.getCustomer()
                        )
                ).get();
                transactionProducer.send(new ProducerRecord<>(
                                Transaction.class.getSimpleName().toLowerCase(),
                                TransactionFactory.getTransaction()
                        )
                ).get();
                Thread.sleep(10000);
            } catch (ExecutionException | InterruptedException e) {

            }
        }
    }

}


