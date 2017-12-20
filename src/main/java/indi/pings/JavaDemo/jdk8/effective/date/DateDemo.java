package indi.pings.JavaDemo.jdk8.effective.date;

import java.time.*;
import java.time.temporal.ChronoField;

/**
 *********************************************************
 ** @desc  ：   新的时间和日期api示例                                          
 ** @author  Pings                                     
 ** @date    2017年12月20日  
 ** @version v1.0                                                                                  
 * *******************************************************
 */
public class DateDemo {
	
	/**LocalDate示例*/
	public static void localDate() {
		//**创建一个LocalDate实例
		LocalDate date = LocalDate.of(2017, 12, 20);
		//**可以向parse方法传递一个DateTimeFormatter,该类的实例定义了如何格式化一个日期或者时间对象
		LocalDate date1 = LocalDate.parse("2014-03-18");
		
		//**1.读取常用的值：年、月、日、星期几、本月有多少天，是否润年
		int year = date.getYear();
		Month month = date.getMonth();
		int day = date.getDayOfMonth();
		DayOfWeek dow = date.getDayOfWeek();
		int len = date.lengthOfMonth();
		boolean leap = date.isLeapYear();
		System.out.println(year + ":" + month + ":" + day + ":" + dow + ":" + len + ":" + leap);
		//**2.通过传递一个TemporalField参数给get方法拿到同样的信息
		int year1 = date.get(ChronoField.YEAR);
		int month1 = date.get(ChronoField.MONTH_OF_YEAR);
		int day1 = date.get(ChronoField.DAY_OF_MONTH);
		System.out.println(year1 + ":" + month1 + ":" + day1);
		
		//**当前时间
		LocalDate today = LocalDate.now();
		System.out.println(date1 + ":" + today);
		
	}
	
	/**LocalTime示例*/
	public static void localTime() {
		//**创建一个LocalTime实例
		LocalTime time = LocalTime.of(13, 45, 20);
		LocalTime time1 = LocalTime.parse("13:45:20");
		System.out.println(time + ":" + time1);
		
		//**读取常用的值：时，分，秒
		int hour = time.getHour();
		int minute = time.getMinute();
		int second = time.getSecond();
		System.out.println(hour + ":" + minute + ":" + second);
	}
	
	/**LocalDateTime示例,是LocalDate和LocalTime的合体*/
	public static void localDateTime() {
		LocalDate date = LocalDate.now();
		LocalTime time = LocalTime.now();
		
		//**创建一个LocalTime实例
		LocalDateTime dt1 = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45, 20);
		LocalDateTime dt2 = LocalDateTime.of(date, time);
		LocalDateTime dt3 = date.atTime(13, 45, 20);
		LocalDateTime dt4 = date.atTime(time);
		LocalDateTime dt5 = time.atDate(date);
		System.out.println(dt1 + ":" + dt2 + ":" + dt3 + ":" + dt4 + ":" + dt5);
	}
	
	/**Instant示例,计算机的日期格式*/
	public static void instant() {
		Instant.ofEpochSecond(3);
		Instant.ofEpochSecond(3, 0);
		Instant.ofEpochSecond(2, 1_000_000_000);
		Instant.ofEpochSecond(4, -1_000_000_000);
	}

	public static void main(String[] args) {
		localDate();
		localTime();
		localDateTime();
	}
}
