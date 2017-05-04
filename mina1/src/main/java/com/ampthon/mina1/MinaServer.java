package com.ampthon.mina1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;
import org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MinaServer {
	private static Logger logger = LoggerFactory.getLogger(MinaServer.class);	
	private IoAcceptor acceptor;
	private void run(){
		acceptor = new NioSocketAcceptor();

		ProtocolCodecFilter filter= new ProtocolCodecFilter(new HCoderFactory(Charset.forName("UTF-8")));
		acceptor.getFilterChain().addLast("objectFilter",filter);

		KeepAliveMessageFactory keepAliveMessageFactory = new KeepAliveMessageFactoryImpl();
		KeepAliveFilter keepAliveFilter = new KeepAliveFilter(
				keepAliveMessageFactory, IdleStatus.BOTH_IDLE);
		keepAliveFilter.setForwardEvent(true);
		keepAliveFilter.setRequestInterval(10);
		keepAliveFilter.setRequestTimeoutHandler(KeepAliveRequestTimeoutHandler.CLOSE);
		acceptor.getFilterChain().addLast("hearbeat", keepAliveFilter);
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10); 
		acceptor.setHandler(new RegisterHandler());

		try {
			acceptor.bind(new InetSocketAddress(2909));//port
		} catch (IOException e) {
			logger.info("MinaServer   bind()   error");
			e.printStackTrace();
		}
	}

	private class KeepAliveMessageFactoryImpl implements KeepAliveMessageFactory{
        //项目中信息传递传的是Result类对象，
		public boolean isRequest(IoSession session, Object message) {
			logger.info("MinaServer    keepalive   isRequest()");
			Result result = (Result)message;
			if(result.getResultType().equals("HEARTBEAT_REQUEST")){
				return true;
			}else{
				return false;
			}
		}

		public boolean isResponse(IoSession session, Object message) {
			logger.info("MinaServer     keepalive    isResponse()");
			Result result = (Result)message;
			if(result.getResultType().equals("HEARTBEAT_RESPONSE")){
				return true;
			}else{
				return false;
			}
		}

		public Object getRequest(IoSession session) {
			logger.info("MinaServer     keepalive    getRequest()");
			Result result = new Result("HEARTBEAT_REQUEST");
			return result;
		}

		public Object getResponse(IoSession session, Object request) {
			logger.info("MinaServer     keepalive    getResponse()");
			Result result = new Result("HEARTBEAT_RESPONSE");
			return result;
		}
		
	}
	
	private class RegisterHandler extends IoHandlerAdapter{

	    public void sessionCreated(IoSession session) throws Exception {
	    	logger.info("MinaServer   running  sessionCreated()");
//	    	ReadFuture read = session.read();
//	    	read.awaitUninterruptibly();
//	    	Result result = (Result) read.getMessage();
//	    	logger.info("MinaServer   sessionCreated()   result" + result.getResultType());
	    	
	    }
	    
	    @SuppressWarnings("deprecation")
		public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
            logger.info("MinaServer   running exceptionCaught()");
            session.close(true);
	    }

	    public void messageReceived(IoSession session, Object message) throws Exception {
	        // 当监听端口接收到数据之后，该方法执行，方法体为对接收数据的具体操作
	    	logger.info("MinaServer    running messageReceived()");
	    	System.out.println("message:: " + message.toString());
	    	if(message instanceof Result){
	    		Result result = (Result)message;
	    		System.out.println("result:" + result.toString());
	    	}else{
	    		System.out.println("not result");
	    	}
	    	
	    	System.out.println("Server  received  " + message.toString());
	    }
	}

	public static void main(String[] args) {
		
		MinaServer server = new MinaServer();
		server.run();
		
	}

}
