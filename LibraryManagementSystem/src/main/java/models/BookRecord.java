package main.java.models;

import java.util.Map;

public record BookRecord(long bookQuantity, Map<String, Double> valueByCategory) {

}
