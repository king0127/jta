package com.jsoft.plgue.mapper;

import com.jsoft.plgue.domain.resp.PartPO;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface PartMapper extends Mapper<PartPO> {

    List<PartPO> queryPart();

    void batchUpdate(List<PartPO> poList);
}
