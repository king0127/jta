package com.example.pattern.state;

// 状态上下文： 将所有的状态存放在这里
public class StateContent {

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    private State state;

    public void doAction() {
        state.doAction(this);
    }
}
