package com.example.pattern.decorator;

public class CarImpl implements CarInterface {
    @Override
    public void productCar() {
        System.out.println(" 【生成汽车】 ");
    }

    @Override
    public void carColor() {
        System.out.println(" 【车身颜色】 ");
    }
}
