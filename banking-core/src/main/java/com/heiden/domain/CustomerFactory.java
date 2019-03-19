package com.heiden.domain;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CustomerFactory {
    private static Faker faker = new Faker();
    private static List<Customer> all = new ArrayList<>();

    public static Customer getCustomer() {
        Customer customer = new Customer(
                faker.name().firstName(),
                faker.name().lastName()
        );
        all.add(customer);
        return customer;
    }

    public static Customer getAny() {
        return all.get(
                (new Random()).nextInt(all.size())
        );
    }

}
