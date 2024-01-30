package com.jsoft.rocket.po.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockShopVO implements Serializable {

    private Integer id;

    private String name;

    private Integer num;

}
