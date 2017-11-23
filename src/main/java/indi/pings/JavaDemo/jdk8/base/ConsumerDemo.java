package indi.pings.JavaDemo.jdk8.base;

import java.util.Arrays;
import java.util.List;

/**
 *********************************************************
 ** @desc  ：  Consumer接口示例                                          
 ** @author  Pings                                     
 ** @date    2017年11月23日  
 ** @version v1.0                                                                                  
 * *******************************************************
 */
public class ConsumerDemo {

	@FunctionalInterface
	public interface Consumer<T> {
		void accept(T t);
	}

	public static <T> void forEach(List<T> list, Consumer<T> c) {
		for (T i : list) {
			c.accept(i);
		}
	}

	public static void main(String[] args) {
		forEach(Arrays.asList(1,2,3,4,5), (Integer i) -> System.out.println(i));
	}
}
