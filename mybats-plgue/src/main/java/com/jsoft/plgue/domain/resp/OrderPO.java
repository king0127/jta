package com.jsoft.plgue.domain.resp;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderPO implements Serializable {

    private int id;

    private String name;

    private int is_del;

}
