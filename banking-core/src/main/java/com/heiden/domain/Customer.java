package com.heiden.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
public class Customer {
    @NonNull String first;
    @NonNull String last;
}
