package server;

import Ice.Current;
import demo._PrinterDisp;

public class PrinterI extends _PrinterDisp{  
	  
    /**
	 * 
	 */
	private static final long serialVersionUID = -2077210757580757183L;
    public void printString(String s, Current __current) {  
        System.out.println(s);  
    }

} 
