package com.danbay.socket.mina.codec;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class ByteArrayEncoder extends ProtocolEncoderAdapter
{
  public void encode(IoSession session, Object message, ProtocolEncoderOutput out)
    throws Exception
  {
    byte[] result = (byte[])message;
    byte[] buf = new byte[4];
    buf[0] = ((byte)(result.length >> 24 & 0xFF));
    buf[1] = ((byte)(result.length >> 16 & 0xFF));
    buf[2] = ((byte)(result.length >> 8 & 0xFF));
    buf[3] = ((byte)(result.length >> 0 & 0xFF));

    int sumlen = 4 + result.length;
    IoBuffer buffer = IoBuffer.allocate(sumlen);
    buffer.put(buf);
    buffer.put(result);
    buffer.flip();
    out.write(buffer);
    out.flush();
  }
}