package com.ampthon.mina1;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class HEncoder implements ProtocolEncoder{  
    private final Charset charset;  
  
    public HEncoder(Charset charset) {  
        this.charset = charset;  
    }

	public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
		 CharsetEncoder ce = charset.newEncoder();  
       Result result = (Result) message;  
       IoBuffer buffer = IoBuffer.allocate(100).setAutoExpand(true);
       buffer.putString(result.getResultType(), ce);
//       buffer.putInt(paEntity.getId());  
//       buffer.putString(paEntity.getName(), ce);  
//       buffer.putString(paEntity.getEmailAdress(), ce);  
//       buffer.putInt(paEntity.getSex());  
       buffer.flip();  
       out.write(buffer);  
		
	}

	public void dispose(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		
	}  
  
}  