package com.ampthon.mina1;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public class HDecoder extends CumulativeProtocolDecoder {  
	private final Charset charset;  

	public HDecoder(Charset charset) {  
		this.charset = charset;  
	}  

	@Override
	protected boolean doDecode(IoSession session, IoBuffer message, ProtocolDecoderOutput out) throws Exception {
		CharsetDecoder cd = charset.newDecoder();  
		String type = message.getString(cd);
		Result result = new Result(type);  
		out.write(result);  
		return true;  
	}  
}  