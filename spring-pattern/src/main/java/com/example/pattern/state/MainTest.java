package com.example.pattern.state;

public class MainTest {

    public static void main(String[] args) {

        // 状态上下文
        StateContent content = new StateContent();

        // 创建订单
        CreateOrder createOrder = new CreateOrder();
        content.setState(createOrder);
        content.doAction();

        StockOrder stockOrder = new StockOrder();
        content.setState(stockOrder);
        content.doAction();
    }

}
