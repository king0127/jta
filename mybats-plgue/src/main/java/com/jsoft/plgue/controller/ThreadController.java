package com.jsoft.plgue.controller;

import com.jsoft.plgue.domain.resp.UserPO;
import com.jsoft.plgue.utils.ThreadUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/th")
public class ThreadController {


    @Resource
    private Executor asyncServiceExecutor;

    @Resource
    private ThreadPoolTaskExecutor asyncTaskExecutor;

    @GetMapping("/test")
    public void testTh() throws ExecutionException, InterruptedException {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

        UserPO userPO = UserPO.builder().id(1).name("admin").build();
        ThreadUser.setUser(userPO);

        log.info(" 打印用户信息：{} ", ThreadUser.getUser());
            CompletableFuture<Void> task = CompletableFuture.runAsync(() -> {
                // ServletRequestAttributes对象设置为子线程共享
                RequestContextHolder.setRequestAttributes(servletRequestAttributes, true);
                UserPO userPO2 = UserPO.builder().id(2).name("king").build();
//                ThreadUser.setUser(userPO2);
                printLog();
                ThreadUser.remove();
            }, asyncServiceExecutor);

            CompletableFuture.allOf(task).get();


        asyncTaskExecutor.submit(() -> {
            try {
                printLog();
            } catch (Exception e) {
                log.error("异步信息失败: {}", e.getMessage());
            }
        });

            log.info(" 打印用户信息======：{} ", ThreadUser.getUser());
    }


    private void printLog() {
        log.info(" ====异步====打印用户信息：{} ", ThreadUser.getUser());
    }


    public static void main(String[] args) {

        UserPO userPO = UserPO.builder().id(1).name("admin").build();
        UserPO userPO2 = UserPO.builder().name("admin").build();

        List<UserPO> list = new ArrayList<>();

        list.add(userPO);
        list.add(userPO2);

//        Map<Long, List<Long>> subjectMap = subjectRelationList.stream().collect(Collectors.groupingBy(ModuleSubjectRelation::getSubjectId, Collectors.mapping(ModuleSubjectRelation::getId, Collectors.toList())));


        Map<Integer, List<String>> collect1 = list.stream().collect(Collectors.groupingBy(v -> Optional.ofNullable(v.getId()).orElse(0), Collectors.mapping(UserPO::getName, Collectors.toList())));

//        List<Integer> collect = list.stream().map(UserPO::getId).collect(Collectors.toList());

        System.out.println(collect1);


    }



}
