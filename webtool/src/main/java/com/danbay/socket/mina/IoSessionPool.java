package com.danbay.socket.mina;

import com.danbay.socket.mina.codec.ByteArrayCodecFactory;
import com.danbay.utils.CollectionUtils;
import com.google.common.collect.Maps;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;
import java.util.Map;

/**
 * Created by carter on 2017/3/20. Copyright © 2016 －2017 旦倍科技
 */
public class IoSessionPool {

    private static final InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1",9999);

    private static final  int  poolSize = 1;

    private static Map<String,IoSession> ioSessionMap = Maps.newConcurrentMap();


    private static IoSessionPool ourInstance = new IoSessionPool();

    public static IoSessionPool getInstance() {
        return ourInstance;
    }

    private IoSessionPool() {



    }




    /**
     * 得到客户端会话
     * @return
     */
    public    IoSession getClientSession() {

        if(CollectionUtils.isNullOrEmpty(ioSessionMap))
        {
            for(int i=0;i<poolSize;i++)
            {
                ioSessionMap.put(String.valueOf(i),createSession());
            }
        }

//        long sessionKey =  System.currentTimeMillis()%2;

        String ioSessionKey = String.valueOf(poolSize - 1);
        IoSession ioSession = ioSessionMap.get(ioSessionKey);

        //如果连接不可用，重新建立连接
        if(!ioSession.isActive())
        {
            ioSession=createSession();
            ioSessionMap.put(ioSessionKey,ioSession);
        }
        return ioSession;
    }

    private IoSession createSession() {
        IoConnector clientConnector = new NioSocketConnector();
        clientConnector.getFilterChain().addLast("codec",new ProtocolCodecFilter(new ByteArrayCodecFactory()));
        clientConnector.setHandler(new MinaClientHandler());

        ConnectFuture connectFuture = clientConnector.connect(inetSocketAddress);
        connectFuture.awaitUninterruptibly();
        return connectFuture.getSession();
    }


}
