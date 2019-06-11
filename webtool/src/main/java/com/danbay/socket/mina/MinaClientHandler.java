package com.danbay.socket.mina;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/**
 * Created by carter on 2017/3/18. Copyright © 2016 －2017 旦倍科技
 * 客户端处理连接事件
 */
public class MinaClientHandler extends IoHandlerAdapter {
    /**
     * {@inheritDoc}
     *
     * @param session
     */
    @Override
    public void sessionCreated(IoSession session) throws Exception {
        super.sessionCreated(session);
    }

    /**
     * {@inheritDoc}
     *
     * @param session
     */
    @Override
    public void sessionOpened(IoSession session) throws Exception {
        super.sessionOpened(session);
    }

    /**
     * {@inheritDoc}
     *
     * @param session
     */
    @Override
    public void sessionClosed(IoSession session) throws Exception {
        super.sessionClosed(session);
    }

    /**
     * {@inheritDoc}
     *
     * @param session
     * @param status
     */
    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        super.sessionIdle(session, status);
    }

    /**
     * {@inheritDoc}
     *
     * @param session
     * @param cause
     */
    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        super.exceptionCaught(session, cause);
    }

    /**
     * {@inheritDoc}
     *
     * @param session
     * @param message
     */
    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        super.messageReceived(session, message);


        System.out.println("接受到的消息："+message);

    }

    /**
     * {@inheritDoc}
     *
     * @param session
     * @param message
     */
    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        super.messageSent(session, message);
    }

    /**
     * {@inheritDoc}
     *
     * @param session
     */
    @Override
    public void inputClosed(IoSession session) throws Exception {
        super.inputClosed(session);
    }
}
