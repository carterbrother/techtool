package com.danbay.socket.mina.codec;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;


public class ByteArrayCodecFactory
  implements ProtocolCodecFactory
{
  private ByteArrayDecode decoder;
  private ByteArrayEncoder encoder;

  public ByteArrayCodecFactory()
  {
    this.encoder = new ByteArrayEncoder();
    this.decoder = new ByteArrayDecode();
  }

  public ProtocolEncoder getEncoder(IoSession session) throws Exception {
    return this.encoder;
  }

  public ProtocolDecoder getDecoder(IoSession session) throws Exception
  {
    return this.decoder;
  }
}