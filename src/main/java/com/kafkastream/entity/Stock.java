package com.kafkastream.entity;

import lombok.Builder;

@Builder
public class Stock {
    private String name;
    private String price;
    private int diff;
    private int amount;
}
