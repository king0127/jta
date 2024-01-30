package com.example.pattern.guancha;

public class ObserverTest {


    public static void main(String[] args) {

        Subject subject = new SubjectManager();

        // 添加通知的消息
        subject.addObserver(new MsgSendObserver());
        subject.doMsg();
    }

}
