package com.example.pattern.state;

public class StockOrder implements State {

    @Override
    public void doAction(StateContent stateContent) {
        System.out.println(" 订单出库 ");
        System.out.println(stateContent);
    }
}
