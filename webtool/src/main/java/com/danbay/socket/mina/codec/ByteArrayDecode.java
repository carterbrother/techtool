package com.danbay.socket.mina.codec;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public class ByteArrayDecode extends CumulativeProtocolDecoder
{
  protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out)
    throws Exception
  {
    if (in.remaining() > 4)
    {
      in.mark();
      byte[] buf = new byte[4];
      in.get(buf);

      int len = buf[0] << 24 | buf[1] << 16 | buf[2] << 8 | buf[3];
      if (len < 0) {
        len = 256 + buf[3];
      }
      if (len > 256) {
        System.out.println("len > 256:::" + len);

        in.reset();
        return false;
      }

      if (in.remaining() < len) {
        System.out.println("in.remaining() < len");

        in.reset();
        return false;
      }

      byte[] packArr = new byte[len];
      in.get(packArr, 0, len);

      IoBuffer buffer = IoBuffer.allocate(len);
      buffer.put(packArr);
      buffer.flip();
      out.write(buffer);
      buffer.free();

      if (in.remaining() > 0) {
        return true;
      }
    }

    return false;
  }
}