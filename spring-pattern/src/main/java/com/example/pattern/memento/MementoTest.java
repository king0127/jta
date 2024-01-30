package com.example.pattern.memento;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;

public class MementoTest {

    public static void main(String[] args) throws IntrospectionException {

//        OrderHistory orderHistory = new OrderHistory();
//        orderHistory.setName("华为META40");
//        MementoManger mementoManger = new MementoManger();
//        mementoManger.setMementoOrder(orderHistory.createMemento());
//        orderHistory.setName("苹果13");
//        System.out.println("目前的商品名称："+ orderHistory.getName());
//        orderHistory.restoreMemento(mementoManger.getMementoOrder());
//        System.out.println("回退的商品名称："+orderHistory.getName());

        MoreOrder moreOrder = new MoreOrder();
        moreOrder.setName("test");
        moreOrder.setStatus("1");
        moreOrder.setPrice("12");
        moreOrder.setPlace("aaaa");
        MoreMementoOrder mementoOrder = moreOrder.setMore();
        System.out.println(mementoOrder.getMap());
    }

}
