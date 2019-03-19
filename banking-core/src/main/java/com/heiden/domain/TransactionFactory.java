package com.heiden.domain;

import com.github.javafaker.Faker;

import java.math.BigDecimal;

public class TransactionFactory {

    private static Faker faker = new Faker();

    public static Transaction getTransaction() {
        return new Transaction(
                new BigDecimal(faker.number().randomDouble(2,-40000, 40000)),
                CustomerFactory.getAny(),
                false
        );
    }

}
