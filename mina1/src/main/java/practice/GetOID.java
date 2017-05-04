package practice;

import java.io.IOException;
import org.snmp4j.*;  
import org.snmp4j.transport.*;  
//import <a href="http://lib.csdn.net/base/javase" class='replace_word' title="Java SE知识库" target='_blank' style='color:#df3434; font-weight:bold;'>Java</a>.io.*;  
import org.snmp4j.smi.*;  
import org.snmp4j.mp.*;  
import org.snmp4j.event.*;  
class GetOID{  
    public static void main(String[] args) throws Exception{  
    
    try{  
        //设定CommunityTarget  
        CommunityTarget myTarget = new CommunityTarget();  
        Address deviceAdd = GenericAddress.parse("udp:localhost/161"); //定义远程主机的地址  
        //Address deviceAdd=new IpAddress("udp:127.0.0.1/161");  
        myTarget.setAddress(deviceAdd);  //设定远程主机的地址  
        myTarget.setCommunity(new OctetString("public"));   //设置snmp共同体  
        myTarget.setRetries(2);  //设置超时重试次数  
        myTarget.setTimeout(5*60);   //设置超时的时间  
        myTarget.setVersion(SnmpConstants.version2c);//设置snmp版本  
         
        //设定采取的协议  
        TransportMapping transport = new DefaultUdpTransportMapping();  //设定传输协议为UDP  
        transport.listen();  
        Snmp protocol = new Snmp(transport);  
         
        //获取mib  
        PDU request = new PDU();  
         
        request.add(new VariableBinding(new OID("1.3.6.1.2.1.1.1")));  
        request.add(new VariableBinding(new OID(new int[] {1,3,6,1,2,1,1,2})));  
        
        request.setType(PDU.GETNEXT);  
        ResponseEvent responseEvent = protocol.send(request, myTarget);  
        PDU response=responseEvent.getResponse();  
        //输出  
        System.out.println("request.size()="+request.size());  
        if(response != null){  
            System.out.println("response.size()="+response.size());  
            VariableBinding vb1 = response.get(0);  
            VariableBinding vb2 = response.get(1);  
            System.out.println(vb1);  
            System.out.println(vb2);  
            transport.close();  
        }  else{
        	System.out.println("response is null");
        }
        
      }catch(IOException e){  
          e.printStackTrace();  
          
      }  
    }  
}  
