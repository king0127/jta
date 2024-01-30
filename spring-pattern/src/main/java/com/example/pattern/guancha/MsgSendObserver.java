package com.example.pattern.guancha;

public class MsgSendObserver  implements Observer {

    @Override
    public void update() {

        System.out.println(" 接受到通知，发送飞书消息！ ");

    }
}
