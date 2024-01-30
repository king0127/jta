package com.example.pattern.memento;

import lombok.Data;

import java.util.Map;

@Data
public class MoreMementoOrder {
    private Map map;

    public MoreMementoOrder(Map map) {
        this.map = map;
    }

}
