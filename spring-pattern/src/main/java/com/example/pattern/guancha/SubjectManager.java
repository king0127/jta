package com.example.pattern.guancha;

public class SubjectManager extends Subject {

    @Override
    public void doMsg() {
        System.out.println(" ===================消息投递===================== ");
        this.notifyObserver();
    }

}
