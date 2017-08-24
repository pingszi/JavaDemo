package indi.pings.JavaDemo.jvm.OutOfMemoryError;

import java.util.ArrayList;
import java.util.List;

/**
 *********************************************************
 ** @desc  ：  Java堆溢出
 **          Java堆用于存储对象实例， 只要不断地创建对象， 并且保证GC Roots到对象之间有可达路径来避免垃圾回收机
 **	                           制清除这些对象， 那么在对象数量到达最大堆的容量限制后就会产生内存溢出异常。         
 **	@VM Args -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError   
 **          -Xms20m -Xmx20m：代码限制Java堆的大小为20MB， 不可扩展 
 **          -XX:+HeapDumpOnOutOfMemoryError：虚拟机在出现内存溢出异常时Dump出当前的内存堆转储快照以便事后进行分析                               
 ** @author  Pings                                      
 ** @date    2017年8月22日  
 ** @version v1.0                                                                                  
 * *******************************************************
 */
public class HeapOOM {
	
	static class OOMObject {}

	public static void main(String[] args) {
		List<OOMObject> list = new ArrayList<>();
		while(true) {
			list.add(new OOMObject());
		}
	}
}

/*
java.lang.OutOfMemoryError: Java heap space
Dumping heap to java_pid9872.hprof ...
Heap dump file created [27964827 bytes in 0.141 secs]
Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
*/