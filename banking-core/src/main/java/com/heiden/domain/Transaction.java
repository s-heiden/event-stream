package com.heiden.domain;

import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @NonNull
    BigDecimal change;

    @NonNull Customer customer;
    @NonNull Boolean fraud = false;

    @JsonGetter("change")
    public BigDecimal getChange() {
        return change.setScale(2, RoundingMode.HALF_UP);
    }
}
