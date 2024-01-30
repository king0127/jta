package com.example.pattern.decorator;

public class BMWCar implements CarInterface {

    public CarInterface carInterface;

    public BMWCar(CarInterface car) {
        this.carInterface = car;
    }

    @Override
    public void productCar() {
        carInterface.productCar();
    }

    @Override
    public void carColor() {
        carInterface.carColor();
    }
}
