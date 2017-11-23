package indi.pings.JavaDemo.jdk8.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *********************************************************
 ** @desc  ：  Function接口示例                                          
 ** @author  Pings                                     
 ** @date    2017年11月23日  
 ** @version v1.0                                                                                  
 * *******************************************************
 */
public class FunctionDemo {
	
	@FunctionalInterface
	public interface Function<T, R> {
		R apply(T t);
	}

	public static <T, R> List<R> map(List<T> list, Function<T, R> f) {
		List<R> result = new ArrayList<>();
		for (T s : list) {
			result.add(f.apply(s));
		}
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(map(Arrays.asList("lambdas","in","action"), (String s) -> s.length()));
	}
}
