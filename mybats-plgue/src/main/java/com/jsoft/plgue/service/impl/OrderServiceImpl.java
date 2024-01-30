package com.jsoft.plgue.service.impl;

import com.jsoft.plgue.annotation.RetryDot;
import com.jsoft.plgue.domain.resp.NumPO;
import com.jsoft.plgue.exception.BigException;
import com.jsoft.plgue.openfeign.CommonFeign;
import com.jsoft.plgue.openfeign.DynamicClient;
import com.jsoft.plgue.service.OrderService;
import com.jsoft.plgue.utils.RedisLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {


    @Resource
    private RedisTemplate redisTemplate;

//    @Resource
//    private OrderMapper orderMapper;

    @Resource
    private RedisLock redisLock;



    @Override
    public void queryListById(int i) {
//        List<OrderPO> orderPOS = orderMapper.queryListById(i);
//        System.out.println(orderPOS);
    }

    @Override
    public void updateById(String name) {
//        orderMapper.updateById(2,name);
    }


    private static Object codeLock = null;

    private AtomicInteger unNum = new AtomicInteger(0);

    @Override
    @RetryDot(retryTimes = 3, retryFor = BigException.class)
    public void getNum(String value) {

//        long startTime = System.currentTimeMillis();
//        if(true) {
//            log.info(" 测试重试操作 ");
//            throw new BigException();
//        }
//        log.info(" 【生产单号开始时间】：{} ", new Date());
//        String firstMajor = "Q/LiA";
////        value = UUID.randomUUID().toString();
//        boolean success = redisTemplate.opsForValue().setIfAbsent(firstMajor, value, 5, TimeUnit.SECONDS);
//        try {
//            if(success) {
//                log.info(" 获取到redis锁：{} ", success);
//                // 守护线程延续时间
////                Thread.sleep(120000);
//                redisLock.renewExpiration(firstMajor, value, 5);
//                NumPO copyCode = getCopyCode(firstMajor);
//                orderMapper.insert(copyCode);
//            } else {
//                log.info(" {}-->获取锁失败，进行重试 ", value);
//                throw new BigException();
//            }
//            log.info(" 订单规则编号消耗时间：{} ", System.currentTimeMillis() - startTime);
//        } catch (Exception e) {
//            if(e instanceof BigException) {
//                throw new BigException();
//            }
//        } finally {
//            redisLock.releaseLock(firstMajor, value);
//        }
    }

    @Override
    public NumPO getCopyCode(String key) {

            NumPO numPO = new NumPO();
            numPO.setKey(key);
            numPO.setNum(getComStaMaxSerialNumber(key));
            return numPO;
    }

    @Override
    public void saveNum() {
//        Integer maxId = orderMapper.getMaxId();
        Integer maxId = 200;
        log.info(" 获取最大的编号：{} ", maxId);
        NumPO numPO = NumPO.builder()
                .id(maxId+1)
                .num(1)
                .version(1)
                .key("KFC")
                .is_del(0)
                .build();
        NumPO numPO2 = NumPO.builder()
                .id(maxId+2)
                .num(1)
                .version(1)
                .key("KFC")
                .is_del(0)
                .build();
        List<NumPO> list = new ArrayList<>();
        list.add(numPO);
        list.add(numPO2);
//        orderMapper.saveNum(list);
    }

//    @Resource
//    private NumMapper numMapper;

    @Resource
    private DynamicClient dynamicClient;

    @Resource
    private CommonFeign commonFeign;

    @Override
    public void getNumList() {

        String id = "1";


//        QueryWrapper<NumPO> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("id", id);
//        List<NumPO> list1 = numMapper.selectList(queryWrapper);
//        http://localhost:1100/api/order/list

        /*System.out.println("=====开始调用=====>");

        String list = commonFeign.getList();
        System.out.println("======调用结束====>"+list);

        System.out.println("=====builder 开始调用=====>");
        Object result = dynamicClient.executePostApi("spring-redis", "/api/order/list");
        System.out.println("======builder 调用结束====>"+result);*/


//        List<NumPO> list = orderMapper.getNumList(Arrays.asList("1", "2", "3"));

//        System.out.println(list1);

//        Map<String, String> map = new HashMap<>();
//        map.put("id", "1");
//        List<NumPO> list2 =orderMapper.getNumList2(map);

//        MyExample example = new MyExample(NumPO.class.newInstance());
//        Weekend<NumPO> weekend = new Weekend<>(NumPO.class);
//        weekend.weekendCriteria().andEqualTo(NumPO::getId, id);
//        List list1 = numMapper.selectByExample(weekend);

        NumPO numPO = new NumPO();
        numPO.setId(1);
        numPO.setKey("KFC");
//        NumPO list1 = numMapper.getComStaMaxSerialNumber(numPO);

//        System.out.println(list1);

    }

    private int getComStaMaxSerialNumber(String key) {
//        NumPO numPO = orderMapper.getComStaMaxSerialNumber(key);
//        if(null != numPO) {
//            return numPO.getNum() + 1;
//        }
        return 0;
    }


    @Override
    public String getDyFunc(String str, String meetName) {
        return str.concat("动态调用方法测试").concat(meetName);
    }

    @Override
    public NumPO getDyNum(NumPO numPO) {
        numPO.setId(2000);
        numPO.setNum(123);
        numPO.setVersion(3);
        numPO.setKey("KK");
        numPO.setIs_del(0);
        return numPO;
    }
}
