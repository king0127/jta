package com.jsoft.ws.config;

import com.alibaba.fastjson.JSON;
import com.jsoft.ws.param.UserDTO;
import com.jsoft.ws.service.MeetingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.security.auth.message.MessageInfo;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
@ServerEndpoint("/api/notice/{id}")
public class WebSocketServer {

    /**
     * 解决无法注入bean：定义静态service对象，通过@Autowired在系统启动时为静态变量赋值
     * @Autowired 注解作用在方法上，如果方法没有参数，spring容器会在类加载完后执行一次这个方法，
     * 如果方法中有参数的话，还会从容器中自动注入这个方法的参数，然后执行一次这个方法。
     */
    public static MeetingService meetingService;

    @Autowired
    public void setXxService(MeetingService meetingService){
        WebSocketServer.meetingService = meetingService;
    }

    //存储客户端session信息
    public static Map<String, Session> clients = new ConcurrentHashMap<>();

    //存储把不同用户的客户端session信息集合
    public static Map<String, Set<String>> connection = new ConcurrentHashMap<>();

    //会话id
    private String sid = null;

    //建立连接的用户id
    private String userId;

    /**
     * @description: 当与前端的websocket连接成功时，执行该方法
     * @PathParam 获取ServerEndpoint路径中的占位符信息类似 控制层的 @PathVariable注解
     **/
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId){
        this.sid = UUID.randomUUID().toString();
        this.userId = userId;
        clients.put(this.sid,session);
        //判断该用户是否存在会话信息，不存在则添加
        Set<String> clientSet = connection.get(userId);
        if (clientSet == null){
            clientSet = new HashSet<>();
            connection.put(userId,clientSet);
        }
        clientSet.add(this.sid);
        log.info(this.userId + "用户建立连接，" + this.sid+"连接开启！");
    }

    /**
     * @description: 当连接失败时，执行该方法
     **/
    @OnClose
    public void onClose(){
        clients.remove(this.sid);
        log.info(this.sid+"连接断开");
    }

    /**
     * @description: 当收到前台发送的消息时，执行该方法
     **/
    @OnMessage
    public void onMessage(String message,Session session) {
        log.info("收到来自用户：" + this.userId + "的信息   " + message);
        //自定义消息实体
        UserDTO viewQueryInfoDTO = JSON.parseObject(message, UserDTO.class);
        viewQueryInfoDTO.setUserId(this.userId);
        //判断该次请求的消息类型是心跳检测还是获取信息
        if (viewQueryInfoDTO.getType().equals("heartbeat")){
            //立刻向前台发送消息，代表后台正常运行
//            sendMessageByUserId(this.userId,new MessageInfo("heartbeat","ok"));
        }
        if (viewQueryInfoDTO.getType().equals("message")){
            //执行业务逻辑
            MessageInfo messageInfo = meetingService.list(viewQueryInfoDTO);
            sendMessageByUserId(this.userId,messageInfo);
        }
    }

    /**
     * @description: 当连接发生错误时，执行该方法
     **/
    @OnError
    public void onError(Throwable error){
        log.info("系统错误");
        error.printStackTrace();
    }

    /**
     * @description: 通过userId向用户发送信息
     * 该类定义成静态可以配合定时任务实现定时推送
     **/
    public static void sendMessageByUserId(String userId, MessageInfo message){
        if (!StringUtils.isEmpty(userId)) {
            Set<String> clientSet = connection.get(userId);
            //用户是否存在客户端连接
            if (Objects.nonNull(clientSet)) {
                Iterator<String> iterator = clientSet.iterator();
                while (iterator.hasNext()) {
                    String sid = iterator.next();
                    Session session = clients.get(sid);
                    //向每个会话发送消息
                    if (Objects.nonNull(session)){
                        try {
                            String jsonString = JSON.toJSONString(message);
                            //同步发送数据，需要等上一个sendText发送完成才执行下一个发送
                            session.getBasicRemote().sendText(jsonString);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

}
