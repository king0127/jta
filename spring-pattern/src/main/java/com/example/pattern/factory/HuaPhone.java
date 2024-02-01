package com.example.pattern.factory;

import org.springframework.stereotype.Service;

@Service
public class HuaPhone implements FSKPhone {

    @Override
    public void makePhone() {
        // 制造华为手机
    }
}
