package com.danbay.websocket;

import com.danbay.utils.HttpUtils;
import com.google.common.collect.Maps;
import org.apache.mina.util.CopyOnWriteMap;

import javax.websocket.Session;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by carter on 2017/6/12. Copyright © 2016 －2017 旦倍科技
 */
public class DemoTask extends Thread {

    public static CopyOnWriteMap<String,Session> sessionCopyOnWriteMap = new CopyOnWriteMap<>(10);


    private AtomicReference commandType = new AtomicReference();

    private Session session = null;

    public DemoTask(String commandType,Session session)
    {
        this.commandType.set(commandType);
        this.session = session;
        sessionCopyOnWriteMap.put(session.getId(),session);
    }


    @Override
    public void run() {

        //处理命令任务；
        if(commandType.get().equals("new_psw"))
        {
            System.out.println("处理新增密码任务");
        }else if(commandType.get().equals("edit_psw"))
        {
            System.out.println("处理编辑密码任务");
        }else{
            return;
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //异步通知接口；
        Map<String,Object> paraMap = Maps.newHashMap();
        paraMap.put("sessionId",session.getId());
        paraMap.put("commandType",commandType.get());
        paraMap.put("commandResult","ok");

        HttpUtils.post("http://127.0.0.1:8080/wsnotify.do",paraMap);

    }
}
