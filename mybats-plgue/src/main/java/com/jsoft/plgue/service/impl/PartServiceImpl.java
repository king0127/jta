package com.jsoft.plgue.service.impl;

import com.jsoft.plgue.domain.resp.PartPO;
import com.jsoft.plgue.mapper.PartMapper;
import com.jsoft.plgue.service.PartService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PartServiceImpl implements PartService {

    @Resource
    private PartMapper partMapper;


    final int splitNum = 200;
    private Integer countStep(Integer size) {
        return (size + splitNum-1) / splitNum;
    }

    @Override
    public void updatePart() {

        List<PartPO> partPOS = partMapper.queryPart();
        int totalNum = partPOS.size();
        int limit = countStep(totalNum);
        List<List<PartPO>> collect = Stream.iterate(0, n -> n + 1).limit(limit).parallel()
                .map(a -> partPOS.stream().skip(a * splitNum).limit(splitNum).parallel()
                        .collect(Collectors.toList())).collect(Collectors.toList());
        for (List<PartPO> poList : collect) {
            List<String> partCodeList = poList.stream().map(PartPO::getPartCode).collect(Collectors.toList());
            // 通过code 查询DSC系统 获取该零件对应的品类信息， 根据零部件code分组
            poList.forEach(v -> v.setPartName(v.getPartCode()+"_"+"P"));
            partMapper.batchUpdate(poList);
        }



    }

}
