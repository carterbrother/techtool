package com.danbay.project;

import com.danbay.utils.coap.CoAPUtils;
import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.eclipse.californium.core.coap.Response;
import org.eclipse.californium.core.network.serialization.DataParser;

public class UdpClientHandler extends IoHandlerAdapter {

    private static final Logger log = Logger.getLogger(UdpClientHandler.class);
    @Override
    public void sessionCreated(IoSession session) throws Exception {
        super.sessionCreated(session);
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        super.sessionOpened(session);
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        super.sessionClosed(session);
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        super.sessionIdle(session, status);
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        super.exceptionCaught(session, cause);
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {

        System.out.println("回包了========================================");

        IoBuffer buffer = (IoBuffer) message;
        byte [] bytes = new byte[buffer.remaining()];
        buffer.get(bytes, 0, buffer.remaining());
        DataParser dataParser = new DataParser( bytes );
        Response response = dataParser.parseResponse();
        String responseString = CoAPUtils.getResponseString(response);
        log.info(" UDP返回："+ responseString);

//        DeviceUtil.device_discover_return= responseString;
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        super.messageSent(session, message);
    }

    @Override
    public void inputClosed(IoSession session) throws Exception {
        super.inputClosed(session);
    }
}