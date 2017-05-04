package practice;

import java.io.File;
import java.util.Calendar;

public class Mkdir {

	public static void main(String[] args) {
  
//		File file = new File("D:/pengquan1");
//		if(!file.exists()){
//			file.mkdirs();
//		}
		
		Calendar calendar = Calendar.getInstance();
		System.out.println(calendar.get(calendar.HOUR_OF_DAY));
		System.out.println(calendar.get(calendar.DATE));
		System.out.println(calendar.get(calendar.DAY_OF_MONTH));
		System.out.println(calendar.get(calendar.HOUR));
		System.out.println(calendar.get(calendar.MONTH));
		
		int a = Integer.MAX_VALUE;
		System.out.println(a);
		
//		String ss = String.va1000 * 60;
//		System.out.println(Integer.parseInt(ss));
	}

}
