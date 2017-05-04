package com.ampthon.mina1;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;
import org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MinaClient {
	private static int IDLETIMEOUT = 30;
	private static int HEARTBEATRATE = 10;
	private NioSocketConnector connector;
	private static Logger logger = LoggerFactory.getLogger(MinaClient.class);
	
	private void initConnector(){
		connector = new NioSocketConnector();
		
		// 添加编码过滤器 处理乱码、编码问题 ，当传递的数据是对象时，必须添加该过滤器
		ProtocolCodecFilter filter= new ProtocolCodecFilter(new HCoderFactory(Charset.forName("UTF-8")));   
        connector.getFilterChain().addLast("objectFilter", filter);  

        //添加心跳过滤器,这个可以先不添加
		KeepAliveMessageFactory keepAliveMessageFactory = new KeepAliveMessageFactoryImpl();
		KeepAliveFilter keepAliveFilter = new KeepAliveFilter(
				keepAliveMessageFactory, IdleStatus.BOTH_IDLE);
		keepAliveFilter.setForwardEvent(false);
		keepAliveFilter.setRequestInterval(HEARTBEATRATE);
		keepAliveFilter.setRequestTimeoutHandler(KeepAliveRequestTimeoutHandler.CLOSE);
		connector.getFilterChain().addLast("hearbeat", keepAliveFilter);
		
		connector.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, IDLETIMEOUT);
		connector.setHandler(new Handler());
	}
	
	//维护连接，实现心跳
	private class KeepAliveMessageFactoryImpl implements KeepAliveMessageFactory{
		
		public boolean isRequest(IoSession session, Object message) {
			logger.info("MinaClient  keepalive  isRequest()");
			Result result = (Result)message;
			if(result.getResultType() != null && result.getResultType().equals("HEARTBEAT_REQUEST")){
				return true;
			}else{
			    return false;
			}
		}

		public boolean isResponse(IoSession session, Object message) {
			logger.info("MinaClient  keepalive  isResponse()");
			Result result = (Result)message;
			if(result.getResultType() != null && result.getResultType().equals("HEARTBEAT_RESPONSE")){
				return true;
			}else{
			    return false;
			}
		}

		public Object getRequest(IoSession session) {
			logger.info("MinaClient  keepalive  getRequest()");
			Result result = new Result("HEARTBEAT_REQUEST");
			return result;
		}

		public Object getResponse(IoSession session, Object request) {
			logger.info("MinaClient  keepalive  getResponse()");
			Result result = new Result("HEARTBEAT_RESPONSE");
			return result;
		}
		
	}
	
	private class Handler extends IoHandlerAdapter{

	    public void sessionCreated(IoSession session) throws Exception {
	    	logger.info("MinaClient  sessionCreated()");
	    	Result result = new Result("111");
			session.write(result);
			
			logger.info("MinaClient  sessionCreated()  write()");
	    }
	    
	    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
            logger.info("MinaClient  exceptionCaught()");
	    }

	    public void messageReceived(IoSession session, Object message) throws Exception {
            logger.info("MinaClient    messageReceived()");
            logger.info("MinaClient  messageReceived  --" + message.toString());
	    }
	}

	private void connect(){
		if(connector == null){
			initConnector();
		}

		ConnectFuture future = connector.connect(new InetSocketAddress("127.0.0.1", 2909));
		future.awaitUninterruptibly();
//		session = future.getSession();
	}

	public static void main(String[] args) {
		
		MinaClient client = new MinaClient();
		client.connect();
	
	}

}
