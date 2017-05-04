package com.ampthon.mina1;

public class Test {

	private static String ss ;

	public void setSs(String ss){
		this.ss = ss;	
	}

	public static void main(String[] args) {
		String aa = "qq";
		if(aa.equals(ss)){
			System.out.println("111" + ss + "222");
		}else{
			System.out.println("333");
		}
	}	

}
