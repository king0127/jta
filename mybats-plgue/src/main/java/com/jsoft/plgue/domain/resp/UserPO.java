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
public class UserPO implements Serializable {
    private static final long serialVersionUID = -8973629838564356452L;

    private Integer id;

    private String name;

}
