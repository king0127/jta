package com.example.pattern.memento;

import lombok.Data;

@Data
public class OrderHistory {

    private String name;

    public MementoOrder createMemento() {
        return new MementoOrder(this.name);
    }

    public void restoreMemento(MementoOrder mementoOrder) {
        this.name = mementoOrder.getName();
    }

}
