package com.example.pattern.decorator;

public class BMWCarColor extends BMWCar {
    public BMWCarColor(CarInterface car) {
        super(car);
    }

    @Override
    public void carColor() {

        System.out.println(" 【宝马x5白色】 ");
//        super.carColor();
        this.carInterface.carColor();
    }
}
