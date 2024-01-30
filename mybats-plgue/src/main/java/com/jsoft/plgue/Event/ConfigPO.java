package com.jsoft.plgue.Event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConfigPO {

    private Integer dictId;

    private String clazz;

    private String method;

    // 类型1=同步；2=异步
    private Integer type;



}
