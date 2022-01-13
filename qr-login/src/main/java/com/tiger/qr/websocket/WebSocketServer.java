package com.tiger.qr.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author Zenghu
 * @Date 2021/10/1 15:55
 * @Description
 * @Version: 1.0
 **/
@ServerEndpoint("/websocket/{sid}")
@Component
public class WebSocketServer {

    private static final Logger LOG = LoggerFactory.getLogger(WebSocketServer.class);

    private static AtomicLong counter = new AtomicLong(0);

    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<>();

    private Session session;

    private String sid = "";


    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid) {
        this.session = session;
        webSocketSet.add(this);
        long count = counter.incrementAndGet();
        LOG.info("连接：{}", sid);
        LOG.info("当前人数：{}", count);
        this.sid = sid;
    }

    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        long count = counter.decrementAndGet();
        LOG.info("当前人数：{}", count);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        LOG.info("收到:{} 的消息：{}", sid, message);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        LOG.error("发生错误", throwable);
    }


    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }


    public static void sendInfo(String message, @PathParam("sid") String sid) {
        for (WebSocketServer item : webSocketSet) {
            try {
                //这里可以设定只推送给这个sid的，为null则全部推送
                if (sid == null) {
                    item.sendMessage(message);
                } else if (item.sid.equals(sid)) {
                    item.sendMessage(message);
                }
            } catch (IOException e) {
                continue;
            }
        }
    }
}
