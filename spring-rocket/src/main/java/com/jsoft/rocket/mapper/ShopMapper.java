package com.jsoft.rocket.mapper;

import com.jsoft.rocket.po.OrderPO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShopMapper {


    @Insert({" insert into `order` (name, is_del) values (#{name}, #{isDel}) "})
    void insert(OrderPO copyCode);

}
