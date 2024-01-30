package com.jsoft.plgue.domain.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_num")
public class NumPO {


    private int id;

    private String key;

    private int num;

    private int version;

    private int is_del;
}
