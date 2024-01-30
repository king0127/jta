package com.example.redis.service.impl;

import com.example.redis.config.ApplicationCxt;
import com.example.redis.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService, InitializingBean {

    public OrderServiceImpl getThis() {
        return ApplicationCxt.getBean(OrderServiceImpl.class);
    }


    @Transactional
    @Override
    public void save() {
        String transactionName = TransactionSynchronizationManager.getCurrentTransactionName();
        log.info(" 测试-事务名称：{} ", transactionName);
      try {
          Thread.sleep(3000);
          getThis().test();
          getThis().test1();
      } catch (InterruptedException e) {
          e.printStackTrace();
      }
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void test() {

        String currentTransactionName = TransactionSynchronizationManager.getCurrentTransactionName();
        log.info(" ----------------无事务:{} --------------- ", currentTransactionName);
    }

    public void test1() {
        String currentTransactionName = TransactionSynchronizationManager.getCurrentTransactionName();
        log.info(" ----------------传播事务名称：{} --------------- ", currentTransactionName);

    }


    @Override
    public void afterPropertiesSet() throws Exception {

        log.info(" spring 初始化bean后执行的逻辑 ");
    }
}
