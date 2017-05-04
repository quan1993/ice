package practice;

import java.util.Calendar;  
import java.util.Date;  
import java.util.GregorianCalendar;  
  
  
public class CalendarTest {  
  
    public static void main(String[] args) {  
    	Calendar calendar = Calendar.getInstance();  
        System.out.println("现在时间："+ calendar.getTime().toString());  
        /** 
         * Calendar中有set方法和get方法 
         * get方法将根据传入的参数值反回相应的值 
         * Calendar.DATE反回的是天数 
         * Calendar.MONTH返回的是月 
         */  
        //获得当前时间之前一周时间点  
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 7);  
        System.out.println("一周之前时间："+calendar.getTime().toString());  
          
        //1个月零一周以前的时间  
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);  
        System.out.println("一个月零一周以前的时间"+calendar.getTime().toString());  
          
    }  
}  