package indi.pings.JavaDemo.jvm.tool;

import java.util.Map;

/**
 *********************************************************
 ** @desc  ：  获取虚拟机中所有线程的StackTraceElement对象，查看线程堆栈                                         
 ** @author  Pings                                     
 ** @date    2017年9月7日  
 ** @version v1.0                                                                                  
 * *******************************************************
 */
public class GetAllStackTraces {
	
	public static void printAllStackTraces() {
		for (Map.Entry<Thread, StackTraceElement[]> stackTrace : Thread.getAllStackTraces().entrySet()) {
			Thread thread = (Thread) stackTrace.getKey();
			StackTraceElement[] stack = (StackTraceElement[]) stackTrace.getValue();
			if (thread.equals(Thread.currentThread())) {
				continue;
			}
			System.out.print("\n线程： " + thread.getName() + "\n");
			for (StackTraceElement element : stack) {
				System.out.print("\t" + element + "\n");
			}
		}
	}
	
	public static void main(String[] args) {
		printAllStackTraces();
	}
}
