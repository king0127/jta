package com.example.pattern.state;

public class CreateOrder implements State {

    @Override
    public void doAction(StateContent stateContent) {
        System.out.println("创建订单");
    }
}
