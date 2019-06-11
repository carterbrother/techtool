package com.danbay.websocket;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

/**
 * Created by carter on 2017/6/10. Copyright © 2016 －2017 旦倍科技
 */
@ServerEndpoint("/wstest")
public class SycDemo {


    //打开连接
    @OnOpen
    public void onOpen(Session session)
    {
        System.out.println("打开webSocket连接...");
    }

    //客户端发送消息；
    @OnMessage
    public void onMessage(String message, Session session)
    {
        //接受数据，进行异步处理；
        System.out.println("接受的参数：" + message);

        //进行任务处理；
        new DemoTask(message,session).start();


        //返回消息；
        session.getAsyncRemote().sendText("同步回复消息", (SendResult sendResult) -> {
            if(sendResult.isOK())
            {
                System.out.println("发送给客户端的信息成功");
            }else {
                System.out.println(sendResult.getException().getMessage());
            }
        });





    }


    @OnClose
    public void onClose(Session session)
    {
        System.out.println("关闭webSocket连接");
    }

    @OnError
    public void onError(Session session, Throwable error)
    {
        System.out.println("webSocket连接出错");
    }




}
