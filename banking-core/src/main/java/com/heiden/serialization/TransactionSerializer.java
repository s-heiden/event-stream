package com.heiden.serialization;

import com.heiden.domain.Transaction;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class TransactionSerializer implements Serializer<Transaction> {

    private TSerializer<Transaction> tSerializer = new TSerializer<>();

    @Override
    public byte[] serialize(String arg0, Transaction data) {
        return tSerializer.serialize(arg0, data);
    }

    @Override
    public void close() {
        tSerializer.close();
    }

    @Override
    public void configure(Map<String, ?> arg0, boolean arg1) {
        tSerializer.configure(arg0, arg1);
    }
}
