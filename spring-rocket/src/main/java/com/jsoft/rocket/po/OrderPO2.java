package com.jsoft.rocket.po;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderPO2 {

    private int id;

    private String name;

    private String orderCode;

    private LocalDateTime createTime;

    private String tag;

}
