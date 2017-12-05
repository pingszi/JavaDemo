package indi.pings.JavaDemo.jdk8.function.parallel;

import java.util.stream.Stream;

/**
 *********************************************************
 ** @desc  ：  并行流示例                                           
 ** @author  Pings                                     
 ** @date    2017年12月1日  
 ** @version v1.0                                                                                  
 * *******************************************************
 */
public class ParallelDemo {
	
	/**100累加*/
	public static void test1() {
		long sum = Stream.iterate(1L, i -> i + 1)
					.limit(100)
					.parallel() //**开启并行流
					.reduce(0L, Long::sum);
		System.out.println(sum);
	}

	public static void main(String[] args) {
		test1();
	}
}
