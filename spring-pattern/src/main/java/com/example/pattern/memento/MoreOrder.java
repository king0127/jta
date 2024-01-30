package com.example.pattern.memento;

import lombok.Data;

@Data
public class MoreOrder {

    private String name;

    private String price;

    private String place;

    private String status;

    public MoreMementoOrder setMore() {
        return new MoreMementoOrder(BeanUtils.backupProp(this));
    }

}
