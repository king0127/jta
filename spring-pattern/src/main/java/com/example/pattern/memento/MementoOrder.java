package com.example.pattern.memento;

import lombok.Data;

@Data
public class MementoOrder {


    private String name;

    public MementoOrder(String name) {
        this.name = name;
    }

}
