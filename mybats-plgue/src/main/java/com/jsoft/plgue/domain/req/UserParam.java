package com.jsoft.plgue.domain.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class UserParam implements UserBuilder {

    private Integer id;

    private String name;

    private String address;


    @Override
    public UserParam saveBuilder(UserParam param) {
        this.name = param.getName();
        this.address = param.getAddress();
        return this;
    }

    @Override
    public UserParam updateBuilder(UserParam param) {
        this.id = param.getId();
        this.name = param.getName();
        this.address = param.getAddress();
        return this;
    }
}
