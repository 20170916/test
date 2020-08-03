package com.example.websocket.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.io.IOException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
//该注解用来指定一个URI，客户端可以通过这个URI来连接到WebSocket。
/**
 类似Servlet的注解mapping。无需在web.xml中配置。
 * configurator = SpringConfigurator.class是为了使该类可以通过Spring注入。
 */
@Slf4j
@ServerEndpoint(value = "/websocket/{userId}")
@Component
public class MyWebSocket {
    private static String userId;

    //连接时执行
    @OnOpen
    public void onOpen(@PathParam("userId") String userId, Session session) throws IOException{
        this.userId = userId;
        log.info("新连接：{}",userId);
    }

    //关闭时执行
    @OnClose
    public void onClose(){
        log.info("连接：{} 关闭",this.userId);
    }

    //收到消息时执行
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        log.info("收到用户{}的消息{}",this.userId,message);
        session.getBasicRemote().sendText("收到 "+this.userId+" 的消息 "); //回复用户
    }

    //连接错误时执行
    @OnError
    public void onError(Session session, Throwable error){
        log.debug("用户id为：{}的连接发送错误",this.userId);
        error.printStackTrace();
    }
}
