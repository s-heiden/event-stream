package com.heiden.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class CustomerBalance {
    Customer customer;
    BigDecimal balance;
}
