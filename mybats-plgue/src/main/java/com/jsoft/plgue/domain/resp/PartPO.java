package com.jsoft.plgue.domain.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PartPO implements Serializable {
    private static final long serialVersionUID = -6734924397196335103L;

    private int id;

    private String partCode;

    private String partName;

    private int isDel;


}
