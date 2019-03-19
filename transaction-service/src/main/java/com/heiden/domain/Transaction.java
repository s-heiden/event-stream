package com.heiden.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "change",
        "customer",
        "fraud"
})
@Data
public class Transaction {
    private BigDecimal change;
    private Customer customer;
    private Boolean fraud;
}