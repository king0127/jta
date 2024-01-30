package com.jsoft.plgue.service;

import com.jsoft.plgue.domain.resp.NumPO;

public interface OrderService {
    void queryListById(int i);

    void updateById(String name);

    void getNum(String value);

    NumPO getCopyCode(String key);

    void saveNum();

    String getDyFunc(String str, String meetName);

    void getNumList() throws InstantiationException, IllegalAccessException;


    NumPO getDyNum(NumPO numPO);
}
